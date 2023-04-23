package com.chenle.gulimall.coupon.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.coupon.entity.SpuBoundsEntity;

import java.util.Map;

/**
 * 商品spu积分设置
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:01:12
 */
public interface SpuBoundsService extends IService<SpuBoundsEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

