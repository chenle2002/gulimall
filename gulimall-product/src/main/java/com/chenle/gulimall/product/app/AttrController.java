package com.chenle.gulimall.product.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.chenle.gulimall.product.entity.ProductAttrValueEntity;
import com.chenle.gulimall.product.service.AttrService;
import com.chenle.gulimall.product.service.ProductAttrValueService;
import com.chenle.gulimall.product.vo.AttrRespVo;
import com.chenle.gulimall.product.vo.AttrVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.R;



/**
 * 商品属性
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@RestController
@Slf4j
@RequestMapping("product/attr")
public class AttrController {
    @Autowired
    private AttrService attrService;

    @Autowired
    ProductAttrValueService productAttrValueService;

    /**
     *  获取spu规格
     */
    @GetMapping("/base/listforspu/{spuId}")
    public R baseAttrlistforspu(@PathVariable("spuId") Long spuId){

        List<ProductAttrValueEntity> entities = productAttrValueService.baseAttrListforspu(spuId);

        return R.ok().put("data",entities);
    }


    ///product/attr/update/{spuId}
    @PostMapping("/update/{spuId}")
    //@RequiresPermissions("product:attr:update")
    public R updateSpuAttr(@PathVariable("spuId") Long spuId,
                           @RequestBody List<ProductAttrValueEntity> entities){

        productAttrValueService.updateSpuAttr(spuId,entities);

        return R.ok();
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = attrService.queryPage(params);

        return R.ok().put("page", page);
    }

    @RequestMapping("/{attrType}/list/{catelogId}")
    public R baselist(@RequestParam Map<String, Object> params,
                      @PathVariable("catelogId") Long catelogId,
                      @PathVariable("attrType") String attrType){
        PageUtils page=attrService.queryBaseAttrPage(params,catelogId,attrType);

        return R.ok().put("page", page);
    }
    /**
     * 信息
     */
    @RequestMapping("/info/{attrId}")
    public R info(@PathVariable("attrId") Long attrId){
//		AttrEntity attr = attrService.getById(attrId);
        log.info("222");
        AttrRespVo attr=attrService.getAttrInfo(attrId);
        return R.ok().put("attr", attr);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrVo attr){
		attrService.saveAttr(attr);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrVo attr){
		attrService.updateAttr(attr);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrIds){
		attrService.removeByIds(Arrays.asList(attrIds));

        return R.ok();
    }


}
