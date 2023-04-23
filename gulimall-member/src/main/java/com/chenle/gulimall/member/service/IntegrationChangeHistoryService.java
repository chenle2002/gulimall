package com.chenle.gulimall.member.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.member.entity.IntegrationChangeHistoryEntity;

import java.util.Map;

/**
 * 积分变化历史记录
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:10:41
 */
public interface IntegrationChangeHistoryService extends IService<IntegrationChangeHistoryEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

