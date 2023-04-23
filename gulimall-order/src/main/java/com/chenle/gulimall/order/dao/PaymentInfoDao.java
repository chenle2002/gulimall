package com.chenle.gulimall.order.dao;

import com.chenle.gulimall.order.entity.PaymentInfoEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 支付信息表
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:26:12
 */
@Mapper
public interface PaymentInfoDao extends BaseMapper<PaymentInfoEntity> {
	
}
