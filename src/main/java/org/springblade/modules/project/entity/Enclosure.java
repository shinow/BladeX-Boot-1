/*
 *      Copyright (c) 2018-2028, Chill Zhuang All rights reserved.
 *
 *  Redistribution and use in source and binary forms, with or without
 *  modification, are permitted provided that the following conditions are met:
 *
 *  Redistributions of source code must retain the above copyright notice,
 *  this list of conditions and the following disclaimer.
 *  Redistributions in binary form must reproduce the above copyright
 *  notice, this list of conditions and the following disclaimer in the
 *  documentation and/or other materials provided with the distribution.
 *  Neither the name of the dreamlu.net developer nor the names of its
 *  contributors may be used to endorse or promote products derived from
 *  this software without specific prior written permission.
 *  Author: Chill 庄骞 (smallchill@163.com)
 */
package org.springblade.modules.project.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 实体类
 *
 * @author BladeX
 * @since 2021-08-07
 */
@Data
@TableName("project_enclosure")
@ApiModel(value = "Enclosure对象", description = "Enclosure对象")
public class Enclosure implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键
	*/
		@ApiModelProperty(value = "主键")
		private Long id;
	/**
	* 投标信息主键
	*/
		@ApiModelProperty(value = "投标信息主键")
		private Long bidId;
	/**
	* 文件类型
	*/
		@ApiModelProperty(value = "文件类型")
		private String fileType;
	/**
	* 文件名
	*/
		@ApiModelProperty(value = "文件名")
		private String fileName;
	/**
	* 文件路径
	*/
		@ApiModelProperty(value = "文件路径")
		private String filePath;


}
