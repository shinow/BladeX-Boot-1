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
package org.springblade.modules.EnterpriseResource.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import java.time.LocalDate;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * 企业资质证书类别表实体类
 *
 * @author BladeX
 * @since 2022-01-17
 */
@Data
@TableName("resource_aptitude_certificate")
@ApiModel(value = "AptitudeCertificate对象", description = "企业资质证书类别表")
public class AptitudeCertificate implements Serializable {

	private static final long serialVersionUID = 1L;

	/**
	* 主键ID
	*/
		@ApiModelProperty(value = "主键ID")
		@TableId(value = "id", type = IdType.AUTO)
	private Long id;
	/**
	* 证书类别
	*/
		@ApiModelProperty(value = "证书类别")
		private String certificateName;
	/**
	* 创建时间
	*/
		@ApiModelProperty(value = "创建时间")
		private LocalDate createTime;
	/**
	* 用户id
	*/
		@ApiModelProperty(value = "用户id")
		private Long useId;


}
