package com.chenle.gulimall.product.dao;

import com.chenle.gulimall.product.entity.CategoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品三级分类
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@Mapper
public interface CategoryDao extends BaseMapper<CategoryEntity> {
	
}
