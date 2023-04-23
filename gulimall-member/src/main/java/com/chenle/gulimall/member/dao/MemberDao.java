package com.chenle.gulimall.member.dao;

import com.chenle.gulimall.member.entity.MemberEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 会员
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:10:40
 */
@Mapper
public interface MemberDao extends BaseMapper<MemberEntity> {
	
}
