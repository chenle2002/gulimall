package com.chenle.gulimall.product.app;

import java.util.Arrays;
import java.util.Map;

import com.chenle.common.valid.AddGroup;
import com.chenle.common.valid.UpdateGroup;
import com.chenle.common.valid.UpdateStatusGroup;
import com.chenle.gulimall.product.entity.BrandEntity;
import com.chenle.gulimall.product.service.BrandService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.R;


/**
 * 品牌
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@RestController
@RequestMapping("product/brand")
public class BrandController {
    @Autowired
    private BrandService brandService;

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = brandService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{brandId}")
    public R info(@PathVariable("brandId") Long brandId){
		BrandEntity brand = brandService.getById(brandId);

        return R.ok().put("brand", brand);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")//@Valid启用校验注解，紧跟BindingResult保存校验信息
    public R save(@Validated({AddGroup.class}) @RequestBody BrandEntity brand){
//        if(bindingResult.hasErrors()){
//            Map<String,String> map=new HashMap<>();
//            bindingResult.getFieldErrors().forEach((item)->{
//                String message=item.getDefaultMessage();
//                String field=item.getField();
//                map.put(field,message);
//            });
//            return R.error(400,"提交的信息不合法").put("data",map);
//        }
		brandService.save(brand);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@Validated(UpdateGroup.class) @RequestBody BrandEntity brand){
		brandService.updateDetil(brand);

        return R.ok();
    }

    /**
     * 修改STATUS
     */
    @RequestMapping("/update/status")
    public R updatestatus(@Validated(UpdateStatusGroup.class) @RequestBody BrandEntity brand){
        brandService.updateById(brand);

        return R.ok();
    }
    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] brandIds){
		brandService.removeByIds(Arrays.asList(brandIds));

        return R.ok();
    }

}
