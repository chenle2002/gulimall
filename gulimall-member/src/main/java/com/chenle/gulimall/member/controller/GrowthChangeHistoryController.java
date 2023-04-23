package com.chenle.gulimall.member.controller;

import java.util.Arrays;
import java.util.Map;

import com.chenle.gulimall.member.entity.MemberEntity;
import com.chenle.gulimall.member.feign.CouponFeignService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chenle.gulimall.member.entity.GrowthChangeHistoryEntity;
import com.chenle.gulimall.member.service.GrowthChangeHistoryService;
import com.chenle.common.utils.PageUtils;
import com.chenle.common.utils.R;



/**
 * 成长值变化历史记录
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:10:41
 */
@RestController
@RequestMapping("member/growthchangehistory")
public class GrowthChangeHistoryController {
    @Autowired
    private GrowthChangeHistoryService growthChangeHistoryService;

    @Autowired
    CouponFeignService couponFeignService;
    @RequestMapping("/test")
    public R test(){
        MemberEntity member=new MemberEntity();
        member.setNickname("张三");
        R membercoupons = couponFeignService.membercoupons();

        return  R.ok().put("member",member).put("coupons",membercoupons.get("coupons"));
    }

    /**
     * 列表
     */
    @RequestMapping("/list")
    public R list(@RequestParam Map<String, Object> params){
        PageUtils page = growthChangeHistoryService.queryPage(params);

        return R.ok().put("page", page);
    }


    /**
     * 信息
     */
    @RequestMapping("/info/{id}")
    public R info(@PathVariable("id") Long id){
		GrowthChangeHistoryEntity growthChangeHistory = growthChangeHistoryService.getById(id);

        return R.ok().put("growthChangeHistory", growthChangeHistory);
    }

    /**
     * 保存
     */
    @RequestMapping("/save")
    public R save(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.save(growthChangeHistory);

        return R.ok();
    }

    /**
     * 修改
     */
    @RequestMapping("/update")
    public R update(@RequestBody GrowthChangeHistoryEntity growthChangeHistory){
		growthChangeHistoryService.updateById(growthChangeHistory);

        return R.ok();
    }

    /**
     * 删除
     */
    @RequestMapping("/delete")
    public R delete(@RequestBody Long[] ids){
		growthChangeHistoryService.removeByIds(Arrays.asList(ids));

        return R.ok();
    }

}
