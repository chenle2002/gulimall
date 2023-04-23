package com.chenle.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.product.entity.CourserDesEntity;
import com.chenle.gulimall.product.service.CourserDesService;
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
@RequestMapping("product/courserdes")
public class CourserDesController {
    @Autowired
    private CourserDesService courserDesService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courserDesService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Integer id){
		CourserDesEntity courserDes = courserDesService.getById(id);

        return R.ok().put("courserDes", courserDes);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourserDesEntity courserDes){
		courserDesService.save(courserDes);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourserDesEntity courserDes){
		courserDesService.updateById(courserDes);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] ids){
		courserDesService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
