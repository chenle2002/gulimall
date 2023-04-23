package com.chenle.gulimall.ware.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.chenle.common.utils.PageUtils;
import com.chenle.gulimall.ware.entity.PurchaseEntity;
import com.chenle.gulimall.ware.vo.MergeVo;
import com.chenle.gulimall.ware.vo.PurchaseDoneVo;

import java.util.List;
import java.util.Map;

/**
 * 采购信息
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 20:31:18
 */
public interface PurchaseService extends IService<PurchaseEntity> {

    PageUtils queryPage(Map<String, Object> params);

    PageUtils queryPageUnreceive(Map<String, Object> params);

    void mergePurchase(MergeVo mergeVo);

    void received(List<Long> ids);

    void done(PurchaseDoneVo doneVo);
}

