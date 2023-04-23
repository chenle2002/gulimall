package com.chenle.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.product.entity.SortEntity;
import com.chenle.gulimall.product.service.SortService;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.R;



/**
 * 
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-11-22 16:14:53
 */
@RestController
@RequestMapping("product/sort")
public class SortController {
    @Autowired
    private SortService sortService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = sortService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{sortId}")
    public R info(@PathVariable("sortId") Integer sortId){
		SortEntity sort = sortService.getById(sortId);

        return R.ok().put("sort", sort);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody SortEntity sort){
		sortService.save(sort);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody SortEntity sort){
		sortService.updateById(sort);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] sortIds){
		sortService.removeByIds(Arrays.asList(sortIds));

        return R.ok();
    }

}
