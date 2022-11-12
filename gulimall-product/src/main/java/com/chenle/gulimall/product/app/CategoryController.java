package com.chenle.gulimall.product.app;

import java.util.Arrays;
import java.util.List;

import com.chenle.gulimall.product.entity.CategoryEntity;
import com.chenle.gulimall.product.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.common.utils.R;



/**
 * 商品三级分类
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@RestController
@RequestMapping("product/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;

    /**
     * 查出所有分类和子分类，以树形结构组装起来
     */
    @RequestMapping("/list/tree")
    public R list(){
        List<CategoryEntity> entityList =  categoryService.listWithTree();
        return R.ok().put("page", entityList);
    }

    @RequestMapping("/update/sort")
    public R updatesort(@RequestBody CategoryEntity[] category){
        categoryService.updateBatchById(Arrays.asList(category));
        return R.ok();
    }

    /**
     * 信息
     */
    @RequestMapping("/info/{catId}")
    public R info(@PathVariable("catId") Long catId){
		CategoryEntity category = categoryService.getById(catId);

        return R.ok().put("data", category);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody CategoryEntity category){
		categoryService.save(category);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody CategoryEntity category){
		categoryService.updateCascade(category);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")//必需发布post @RequestBody
    public R delete(@RequestBody Long[] catIds){
        //检查当前删除的菜单但=是否被引用


//		categoryService.removeByIds(Arrays.asList(catIds));
		categoryService.removeMenuByIds(Arrays.asList(catIds));

        return R.ok();
    }

}
