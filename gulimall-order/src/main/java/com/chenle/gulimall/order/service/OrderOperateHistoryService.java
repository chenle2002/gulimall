package com.chenle.gulimall.order.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.order.entity.OrderOperateHistoryEntity;

import java.util.Map;

/**
 * 订单操作历史记录
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:26:12
 */
public interface OrderOperateHistoryService extends IService<OrderOperateHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

