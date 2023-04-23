package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.UndoLogEntity;


import java.util.Map;

/**
 * 
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-12 19:52:27
 */
public interface UndoLogService extends IService<UndoLogEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

