package com.chenle.gulimall.product.service.impl;

import com.chenle.gulimall.product.dao.SkuImagesDao;
import com.chenle.gulimall.product.entity.SkuImagesEntity;
import com.chenle.gulimall.product.service.SkuImagesService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;


@Service("skuImagesService")
public class SkuImagesServiceImpl extends ServiceImpl<SkuImagesDao, SkuImagesEntity> implements SkuImagesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SkuImagesEntity> page = this.page(
                new Query<SkuImagesEntity>().getPage(params),
                new QueryWrapper<SkuImagesEntity>()
        );

        return new PageUtils(page);
    }

    @Override
    public List<SkuImagesEntity> getImageBySkuId(Long skuId) {
        SkuImagesDao baseMapper = this.baseMapper;
        return baseMapper.selectList(new QueryWrapper<SkuImagesEntity>().eq("sku_id",skuId));
    }

}