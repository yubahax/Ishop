package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbMember;
import com.Ishop.common.util.util.ParamVail;
import com.Ishop.common.util.util.TimeUtil;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.user.mapper.MemberMapper;
import com.Ishop.user.service.MemberService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class MemberServiceImpl implements MemberService {
    @Resource
    MemberMapper memberMapper;

    @Resource
    Yedis yedis;
    @Override
    public TbMember getVipInfo() {
        return memberMapper.selectById(yedis.getUser(yedis.getName()).getId());
    }

    @Override
    public boolean upToVip() {
        Long id = yedis.getUser(yedis.getName()).getId();
        TbMember tbMember = memberMapper.selectById(id);
        if (ParamVail.isNull(tbMember)) {
            tbMember.setId(id);
            tbMember.setState(0);
            tbMember.setDescription("普通会员");
            tbMember.setPoints(0L);
            tbMember.setStarttime(TimeUtil.getTime());
            tbMember.setEndtime(TimeUtil.addDayTime(30));
            tbMember.setCreated(TimeUtil.getTime());
            tbMember.setUpdated(TimeUtil.getTime());
            return memberMapper.insert(tbMember) == 1;
        } else {
            tbMember.setStarttime(TimeUtil.getTime());
            tbMember.setEndtime(TimeUtil.addDayTime(30));
            return memberMapper.updateById(tbMember) == 1;
        }
    }
}
