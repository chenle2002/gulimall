package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.AttrEntity;
import com.chenle.gulimall.product.vo.AttrGroupRelationVo;
import com.chenle.gulimall.product.vo.AttrRespVo;
import com.chenle.gulimall.product.vo.AttrVo;

import java.util.List;
import java.util.Map;

/**
 * 商品属性
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
public interface AttrService extends IService<AttrEntity> {

    PageUtils queryPage(Map<String, Object> params);

    void saveAttr(AttrVo attr);

    PageUtils queryBaseAttrPage(Map<String, Object> params, Long catelogId,String attrType);

    AttrRespVo getAttrInfo(Long attrId);

    void updateAttr(AttrVo attr);

    List<AttrEntity> getRelationGroup(Long attrgroupid);

    void deleteRelation(AttrGroupRelationVo[] vos);

    PageUtils getNORelationAttr(Map<String, Object> params, Long attrgroupId);

    List<AttrEntity> getRelationAttr(Long attrGroupId);

    List<Long> selectSearchAttrs(List<Long> attrIds);
}

