package com.chenle.gulimall.order.dao;

import com.chenle.gulimall.order.entity.OrderOperateHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单操作历史记录
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:26:12
 */
@Mapper
public interface OrderOperateHistoryDao extends BaseMapper<OrderOperateHistoryEntity> {
	
}
