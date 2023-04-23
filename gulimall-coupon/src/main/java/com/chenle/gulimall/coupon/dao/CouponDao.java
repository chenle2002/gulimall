package com.chenle.gulimall.coupon.dao;

import com.chenle.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:01:13
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
