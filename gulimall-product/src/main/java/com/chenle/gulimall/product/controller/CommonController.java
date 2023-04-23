package com.chenle.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.product.entity.CommonEntity;
import com.chenle.gulimall.product.service.CommonService;
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
@RequestMapping("product/common")
public class CommonController {
    @Autowired
    private CommonService commonService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = commonService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CommonEntity common = commonService.getById(id);

        return R.ok().put("common", common);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CommonEntity common){
		commonService.save(common);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CommonEntity common){
		commonService.updateById(common);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		commonService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
