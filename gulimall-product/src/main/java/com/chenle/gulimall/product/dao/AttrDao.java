package com.chenle.gulimall.product.dao;

import com.chenle.gulimall.product.entity.AttrEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 商品属性
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@Mapper
public interface AttrDao extends BaseMapper<AttrEntity> {

    List<Long> selectsearchAttrIds(@Param("attrIds") List<Long> attrIds);
}
