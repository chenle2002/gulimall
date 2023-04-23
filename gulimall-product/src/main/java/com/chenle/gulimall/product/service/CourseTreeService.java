package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.CourseTreeEntity;

import java.util.List;
import java.util.Map;

/**
 * 
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-11-25 08:50:34
 */
public interface CourseTreeService extends IService<CourseTreeEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CourseTreeEntity> listWithTree();

    PageUtils listgrandfather(Map<String, Object> params);
}

