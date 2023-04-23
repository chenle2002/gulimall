package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.TeacherEntity;

import java.util.Map;

/**
 * 
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-11-22 16:14:53
 */
public interface TeacherService extends IService<TeacherEntity> {

    PageUtils queryPage(Map<String, Object> params);
}

