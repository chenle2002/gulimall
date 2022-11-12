package com.chenle.gulimall.product.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.product.entity.CategoryEntity;
import com.chenle.gulimall.product.vo.Catelog2Vo;

import java.util.List;
import java.util.Map;

/**
 * 商品三级分类
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
public interface CategoryService extends IService<CategoryEntity> {

    PageUtils queryPage(Map<String, Object> params);

    List<CategoryEntity> listWithTree();

    void removeMenuByIds(List<Long> asList);

//    [父/子/孙]
    Long[] findCategoryPath(Long catelogId);

    void updateCascade(CategoryEntity category);

    Long[] findCatelogPath(Long catelogId);

    List<CategoryEntity> getLevel1Categorys();

    Map<String, List<Catelog2Vo>> getCatalogJson();
}

