package com.chenle.gulimall.product.service.impl;

import org.springframework.stereotype.Service;
import java.util.Map;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.Query;

import com.chenle.gulimall.product.dao.CommonDao;
import com.chenle.gulimall.product.entity.CommonEntity;
import com.chenle.gulimall.product.service.CommonService;


@Service("commonService")
public class CommonServiceImpl extends ServiceImpl<CommonDao, CommonEntity> implements CommonService {

    @Override
    public PageUtils queryPage(Map<String, Object> params) {
        IPage<CommonEntity> page = this.page(
                new Query<CommonEntity>().getPage(params),
                new QueryWrapper<CommonEntity>()
        );

        return new PageUtils(page);
    }

}