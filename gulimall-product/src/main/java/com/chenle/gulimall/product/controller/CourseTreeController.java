package com.chenle.gulimall.product.controller;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.chenle.gulimall.product.entity.CategoryEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.product.entity.CourseTreeEntity;
import com.chenle.gulimall.product.service.CourseTreeService;
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
@RequestMapping("product/coursetree")
public class CourseTreeController {
    @Autowired
    private CourseTreeService courseTreeService;

    /**
     * 列表
     */
    @RequestMapping("/list/tree")
    public R listWithTree(@RequestParam Map<String, Object> params){
        List<CourseTreeEntity> entityList =  courseTreeService.listWithTree();
        return R.ok().put("page", entityList);
    }
    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = courseTreeService.queryPage(params);

        return R.ok().put("page", page);
    }
    @RequestMapping("/list/grandfather")
    public R listgrandfather(@RequestParam Map<String, Object> params){
        PageUtils page = courseTreeService.listgrandfather(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{courseId}")
    public R info(@PathVariable("courseId") Integer courseId){
        CourseTreeEntity courseTree = courseTreeService.getById(courseId);

        return R.ok().put("courseTree", courseTree);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CourseTreeEntity courseTree){
        courseTreeService.save(courseTree);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CourseTreeEntity courseTree){
        courseTreeService.updateById(courseTree);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Integer[] courseIds){
        courseTreeService.removeByIds(Arrays.asList(courseIds));

        return R.ok();
    }

}
