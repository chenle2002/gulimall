package com.chenle.gulimall.product.service.impl;

import com.chenle.gulimall.product.dao.BrandDao;
import com.chenle.gulimall.product.entity.BrandEntity;
import com.chenle.gulimall.product.service.BrandService;

import com.chenle.gulimall.product.service.CategoryBrandRelationService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;


@Service("brandService")
public class BrandServiceImpl extends ServiceImpl<BrandDao, BrandEntity> implements BrandService {

    @Autowired
    CategoryBrandRelationService categoryBrandRelationService;
    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        Object key = params.get("key");
        String keyS=(String)key;
        QueryWrapper<BrandEntity> queryWrapper = new QueryWrapper<>();
        if(!StringUtils.isEmpty(keyS)){
            queryWrapper.eq("brand_id",keyS).or().like("name",keyS);
        }
        IPage<BrandEntity> page = this.page(
                new Query<BrandEntity>().getPage(params),
                queryWrapper
        );

        return new PageUtils(page);
    }

    @Override
    public void updateDetil(BrandEntity brand) {
        //保证冗余数据的一致
        this.updateById(brand);

        if(!StringUtils.isEmpty(brand.getName())){
            //同步更新其他关联表数据
            categoryBrandRelationService.updateBrand(brand.getBrandId(),brand.getName());

            //TODO更新其他关联
        }
    }

}