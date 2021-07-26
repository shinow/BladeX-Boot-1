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
package org.springblade.modules.project.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import lombok.AllArgsConstructor;
import org.springblade.common.enums.BidCancelStatusEnum;
import org.springblade.common.enums.BidStatusEnum;
import org.springblade.core.secure.utils.AuthUtil;
import org.springblade.core.tool.utils.Func;
import org.springblade.modules.project.dto.BidApplyDTO;
import org.springblade.modules.project.dto.BidToVoidDTO;
import org.springblade.modules.project.entity.Bid;
import org.springblade.modules.project.entity.Business;
import org.springblade.modules.project.service.IBusinessService;
import org.springblade.modules.project.vo.BidVO;
import org.springblade.modules.project.mapper.BidMapper;
import org.springblade.modules.project.mapper.BusinessMapper;
import org.springblade.modules.project.service.IBidService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

/**
 * 服务实现类
 *
 * @author BladeX
 * @since 2021-07-18
 */
@Service
@AllArgsConstructor
public class BidServiceImpl extends ServiceImpl<BidMapper, Bid> implements IBidService {


	private final IBusinessService businessService;
	private final BusinessMapper businessMapper;

	@Override
	public IPage<BidVO> selectBidPage(IPage<BidVO> page, BidVO bid) {
		return page.setRecords(baseMapper.selectBidPage(page, bid));
	}

	/**
	 * 根据商机主键获取投标信息
	 *
	 * @param businessId 商机主键
	 * @return
	 */
	@Override
	public Bid getBidByBusinessId(long businessId) {

		LambdaQueryWrapper<Bid> queryWrapper = new LambdaQueryWrapper<>();
		queryWrapper.eq(Bid::getBusinessId, businessId);

		Bid bid = baseMapper.selectOne(queryWrapper);

		return bid;
	}

	/**
	 * 终止投标
	 *
	 * @param cancelDTO 终止实体
	 * @return
	 */
	@Override
	public Boolean stopBid(BidToVoidDTO cancelDTO) {
		Bid bid = baseMapper.selectById(cancelDTO.getBidId());

		if (bid != null && !bid.getIsCancel() && bid.getCancelStatus() == BidCancelStatusEnum.WAIT.getValue()) {
			bid.setApplyCancelTime(LocalDateTime.now());
			bid.setApplyCancelUser(AuthUtil.getUser().getUserId());
			bid.setCancelReason(cancelDTO.getReasons());
			bid.setCancelStatus(BidCancelStatusEnum.APPLY.getValue());
			baseMapper.updateById(bid);
			return true;
		}

		return false;
	}

	/**
	 * 发起投标申请
	 *
	 * @param applyDTO
	 * @return
	 */
	@Override
	@Transactional
	public Boolean saveBidApply(BidApplyDTO applyDTO) {


		Bid bid = baseMapper.selectById(applyDTO.getBidId());
		Business business = businessService.getById(bid.getBusinessId());

		business.setMajor(applyDTO.getMajor());
		business.setIndustry(applyDTO.getIndustry());
		business.setExpandMode(applyDTO.getExpandMode());
		business.setClientId(applyDTO.getClientId());
		business.setClientName(applyDTO.getClientName());
		business.setClientCategory(applyDTO.getClientCategory());
		business.setClientType(applyDTO.getClientType());
		business.setClientRelationship(applyDTO.getClientRelationship());
		business.setClientContact(applyDTO.getClientContact());
		business.setClientPhone(applyDTO.getClientPhone());


		bid.setBidCode(applyDTO.getBidCode());
		bid.setProjectName(applyDTO.getProjectName());
		bid.setManagerId(applyDTO.getManagerId());
		bid.setBidAmount(applyDTO.getBidAmount());
		bid.setBidOpenTime(applyDTO.getBidOpenTime());
		bid.setReceiveTime(applyDTO.getReceiveTime());
		bid.setIsNeedBond(applyDTO.getIsNeedBound());
		bid.setIsFrame(applyDTO.getIsFrame());
		bid.setIsAdvancePay(applyDTO.getIsAdvancePay());
		bid.setAdvancePayReason(applyDTO.getAdvancePayReason());
		bid.setBidStatus(BidStatusEnum.APPLY_BID.getValue());


		//附件信息保存


		return businessService.saveOrUpdate(business) && this.saveOrUpdate(bid);
	}

	/**
	 * 推送至投标管理
	 *
	 * @param businessId
	 * @return
	 */
	@Override
	public boolean pushToBid(long businessId) {

		if (Func.isEmpty(businessId)) {
			return false;
		}

		Business record = businessMapper.selectById(businessId);

		if (record != null && Func.isNotEmpty(record.getId())) {

			Bid bid = getBidByBusinessId(record.getId());

			if (bid == null) {
				Bid newBid = new Bid();
				newBid.setBusinessId(record.getId());
				save(newBid);
			}
		}


		return false;
	}
}