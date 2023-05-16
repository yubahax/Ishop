package com.Ishop.user.mapper;

import com.Ishop.common.entity.TbMember;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberMapper extends BaseMapper<TbMember> {
}
