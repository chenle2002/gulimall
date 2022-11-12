package com.chenle.gulimall.product.app;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

import com.chenle.gulimall.product.entity.AttrEntity;
import com.chenle.gulimall.product.entity.AttrGroupEntity;
import com.chenle.gulimall.product.service.AttrAttrgroupRelationService;
import com.chenle.gulimall.product.service.AttrGroupService;
import com.chenle.gulimall.product.service.AttrService;
import com.chenle.gulimall.product.service.CategoryService;
import com.chenle.gulimall.product.vo.AttrGroupRelationVo;
import com.chenle.gulimall.product.vo.AttrGroupWithAttrsVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.R;



/**
 * 属性分组
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@RestController
@Slf4j
@RequestMapping("product/attrgroup")
public class AttrGroupController {
    @Autowired
    private AttrGroupService attrGroupService;

    @Autowired
    private CategoryService categoryService;

    @Autowired
    private AttrService attrService;

    @Autowired
    AttrAttrgroupRelationService relationService;

    @PostMapping("/attr/relation/delete")
    public R deleteRelation(@RequestBody AttrGroupRelationVo[] vos){
        attrService.deleteRelation(vos);
        return R.ok();
    }


    /**
     * 获取属性分组有关联的其他属性
     * @param attrgroupId
     * @return
     */
    @GetMapping(value = "/{attrgroupId}/attr/relation")
    public R attrRelation(@PathVariable("attrgroupId") Long attrgroupId) {

        List<AttrEntity> entities = attrService.getRelationGroup(attrgroupId);

        return R.ok().put("data",entities);
    }

    @GetMapping(value = "/{attrgroupId}/noattr/relation")
    public R attrNoRelation(@PathVariable("attrgroupId") Long attrgroupId,
                            @RequestParam Map<String,Object> params) {

       PageUtils page = attrService.getNORelationAttr(params,attrgroupId);

        return R.ok().put("page",page);
    }

    @PostMapping(value = "/attr/relation")
    public R addRelation(@RequestBody List<AttrGroupRelationVo> vos) {

        relationService.saveBatch(vos);

        return R.ok();
    }


    @GetMapping(value = "/{catelogId}/withattr")
    public R getAttrGroupWithAttrs(@PathVariable("catelogId") Long catelogId) {
        List<AttrGroupWithAttrsVo> attrGroupWithAttrsVos=attrGroupService.getAttrGroupWithAttrsByCatelogId(catelogId);
        return R.ok().put("data",attrGroupWithAttrsVos);
    }


    /**
     * 列表
     */
    @RequestMapping("/list/{catelogId}")
    public R list(@RequestParam Map<String, Object> params,@PathVariable("catelogId") Long catelogId){
//        PageUtils page = attrGroupService.queryPage(params);


        PageUtils pageUtils = attrGroupService.queryPage(params, catelogId);
        return R.ok().put("page", pageUtils);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{attrGroupId}")
    public R info(@PathVariable("attrGroupId") Long attrGroupId){
		AttrGroupEntity attrGroup = attrGroupService.getById(attrGroupId);

        Long catelogId=attrGroup.getCatelogId();

        Long[] path= categoryService.findCategoryPath(catelogId);
        attrGroup.setCatelogPath(path);
        return R.ok().put("attrGroup", attrGroup);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.save(attrGroup);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody AttrGroupEntity attrGroup){
		attrGroupService.updateById(attrGroup);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] attrGroupIds){
		attrGroupService.removeByIds(Arrays.asList(attrGroupIds));

        return R.ok();
    }

}
