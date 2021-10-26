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
package org.springblade.modules.system.service;

import org.springblade.modules.system.entity.Manager;
import org.springblade.modules.system.vo.ManagerVO;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.core.metadata.IPage;

import java.util.List;

/**
 *  服务类
 *
 * @author BladeX
 * @since 2021-06-30
 */
public interface IManagerService extends IService<Manager> {

	/**
	 * 自定义分页
	 *
	 * @param page
	 * @param manager
	 * @return
	 */
	IPage<ManagerVO> selectManagerPage(IPage<ManagerVO> page, ManagerVO manager);

	/*
	* 连表查询项目经理
	* */
	IPage<ManagerVO> selectManagerVOPage(IPage<ManagerVO> page, ManagerVO manager);

	/************************************************************************************************/
	/**
	 *
	 *wucan:2021年10月26日09:16:46
	 *
	 */
	/**
	 * 单查详情以及显示用户名字
	 *
	 * @return
	 */
	Manager selectManagerDetail(Long id);


	/**
	 * 根据项目经理ID查询商机+投标表
	 *
	 * @return
	 */
	List<ManagerVO> selectProjectBusiness(Long id);



	/**
	 * list列表带用户名字
	 * @param page
	 * @param manager
	 * @return
	 */
	IPage<Manager> selectManagerList(IPage page, ManagerVO manager);


}
