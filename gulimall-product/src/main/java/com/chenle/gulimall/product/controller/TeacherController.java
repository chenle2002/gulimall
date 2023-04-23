package com.chenle.gulimall.product.controller;

import java.util.Arrays;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.product.entity.TeacherEntity;
import com.chenle.gulimall.product.service.TeacherService;
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
@RequestMapping("product/teacher")
public class TeacherController {
    @Autowired
    private TeacherService teacherService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = teacherService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{teacherId}")
    public R info(@PathVariable("teacherId") Integer teacherId){
		TeacherEntity teacher = teacherService.getById(teacherId);

        return R.ok().put("teacher", teacher);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody TeacherEntity teacher){
		teacherService.save(teacher);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody TeacherEntity teacher){
		teacherService.updateById(teacher);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] teacherIds){
		teacherService.removeByIds(Arrays.asList(teacherIds));

        return R.ok();
    }

}
