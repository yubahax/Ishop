package com.Ishop.user.service.serviceImpl;

import com.Ishop.common.entity.TbMember;
import com.Ishop.common.util.util.Yedis;
import com.Ishop.user.mapper.MemberMapper;
import com.Ishop.user.service.MemberService;
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




}
