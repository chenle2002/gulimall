package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.AttrGroupEntity;
import com.chenle.gulimall.product.vo.AttrGroupWithAttrsVo;

import java.util.List;
import java.util.Map;

/**
 * 属性分组
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
public interface AttrGroupService extends IService<AttrGroupEntity> {
    PageUtils queryPage(Map<String, Object> params,Long catelogId);

    List<AttrGroupWithAttrsVo> getAttrGroupWithAttrsByCatelogId(Long catelogId);
//    PageUtils queryPage(Map<String, Object> params);
}

