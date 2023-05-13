package com.Ishop.store.mapper;

import com.Ishop.common.entity.TbOrder;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface OrderMapper extends BaseMapper<TbOrder> {
}
