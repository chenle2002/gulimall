package com.chenle.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;

import com.chenle.gulimall.product.dao.SortDao;
import com.chenle.gulimall.product.entity.SortEntity;
import com.chenle.gulimall.product.service.SortService;


@Service("sortService")
public class SortServiceImpl extends ServiceImpl<SortDao, SortEntity> implements SortService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<SortEntity> page = this.page(
                new Query<SortEntity>().getPage(params),
                new QueryWrapper<SortEntity>()
        );

        return new PageUtils(page);
    }

}