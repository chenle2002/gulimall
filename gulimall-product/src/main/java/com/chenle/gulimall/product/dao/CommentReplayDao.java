package com.chenle.gulimall.product.dao;

import com.chenle.gulimall.product.entity.CommentReplayEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 商品评价回复关系
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@Mapper
public interface CommentReplayDao extends BaseMapper<CommentReplayEntity> {
	
}
