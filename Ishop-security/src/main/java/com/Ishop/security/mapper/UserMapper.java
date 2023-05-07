package com.Ishop.security.mapper;


import com.Ishop.common.entity.TbUser;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseMapper<TbUser> {

    @Select("select * from user")
    List<TbUser> getUsers();

}
