package com.chenle.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;

import com.chenle.common.valid.AddGroup;
import com.chenle.common.valid.ListValue;
import com.chenle.common.valid.UpdateGroup;
import com.chenle.common.valid.UpdateStatusGroup;
import lombok.Data;
import org.hibernate.validator.constraints.URL;

import javax.validation.constraints.*;

/**
 * 品牌
 *
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-10-03 19:16:42
 */
@Data
@TableName("pms_brand")
public class BrandEntity implements Serializable {
    private static final long serialVersionUID = 1L;

    /**
     * 品牌id
     */
    @NotNull(message = "修改必须指定id", groups = {UpdateGroup.class})
    @Null(message = "新增不能指定id", groups = {AddGroup.class})
    @TableId
    private Long brandId;
    /**
     * 品牌名
     */
    @NotBlank(message = "品牌名必须提交", groups = {UpdateGroup.class, AddGroup.class})
    private String name;
    /**
     * 品牌logo地址
     */
    @NotNull(groups =  {UpdateGroup.class})
    @URL(message = "LOGO必须是一个合法的URL地址",groups = { AddGroup.class})
    private String logo;
    /**
     * 介绍
     */
    private String descript;
    /**
     * 显示状态[0-不显示；1-显示]
     */
    @NotNull(groups = {UpdateGroup.class, AddGroup.class})
    @ListValue(vals = {0,1},groups = {AddGroup.class, UpdateStatusGroup.class})
    private Integer showStatus;
    /**
     * 检索首字母
     */
    @NotNull(groups =  {UpdateGroup.class})
    @Pattern(regexp = "^[a-zA-Z]$", message = "检索首字母必须是一个字母",groups = {UpdateGroup.class, AddGroup.class})
    private String firstLetter;
    /**
     * 排序
     */
    @NotNull(groups =  {UpdateGroup.class})
    @Min(value = 0, message = "排序必须大于等于0",groups = {UpdateGroup.class, AddGroup.class})
    private Integer sort;

}
