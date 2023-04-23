package com.chenle.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.coupon.entity.CouponSpuRelationEntity;

import java.util.Map;

/**
 * 优惠券与产品关联
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:01:13
 */
public interface CouponSpuRelationService extends IService<CouponSpuRelationEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

