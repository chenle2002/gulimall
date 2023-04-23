package com.chenle.gulimall.product.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;

import java.io.Serializable;
import java.util.Date;
import lombok.Data;

/**
 * 
 * 
 * @author chenle
 * @email chenle@mail.ynu.edu.cn
 * @date 2022-11-22 16:14:53
 */
@Data
@TableName("sort")
public class SortEntity implements Serializable {
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 */
	@TableId
	private Integer sortId;
	/**
	 * 
	 */
	private String name;

}
