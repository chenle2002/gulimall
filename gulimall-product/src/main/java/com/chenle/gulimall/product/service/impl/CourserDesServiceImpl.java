package com.chenle.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;

import com.chenle.gulimall.product.dao.CourserDesDao;
import com.chenle.gulimall.product.entity.CourserDesEntity;
import com.chenle.gulimall.product.service.CourserDesService;


@Service("courserDesService")
public class CourserDesServiceImpl extends ServiceImpl<CourserDesDao, CourserDesEntity> implements CourserDesService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CourserDesEntity> page = this.page(
                new Query<CourserDesEntity>().getPage(params),
                new QueryWrapper<CourserDesEntity>()
        );

        return new PageUtils(page);
    }

}