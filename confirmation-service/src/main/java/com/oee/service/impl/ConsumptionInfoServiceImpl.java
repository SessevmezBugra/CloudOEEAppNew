package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;

import com.oee.client.StockServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.dto.StockDto;
import com.oee.entity.ProdRunHdr;
import com.oee.enums.Status;
import com.oee.error.EntityNotFoundException;
import com.oee.service.ProdRunHdrService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import com.oee.entity.ConsumptionInfo;
import com.oee.repository.ConsumptionInfoRepository;
import com.oee.service.ConsumptionInfoService;

@Service
@RequiredArgsConstructor
public class ConsumptionInfoServiceImpl implements ConsumptionInfoService{

	private final ConsumptionInfoRepository consumptionInfoRepository;
	private final ProdRunHdrService prodRunHdrService;
	private final StockServiceClient stockServiceClient;
	private final CurrentUserProvider currentUserProvider;

	@Override
	public ConsumptionInfo create(ConsumptionInfo consumptionInfo) {
		return consumptionInfoRepository.save(consumptionInfo);
	}

	@Override
	public ConsumptionInfo update(ConsumptionInfo consumptionInfo) {
		return consumptionInfoRepository.save(consumptionInfo);
	}

	@Override
	public Boolean delete(Long id) {
		consumptionInfoRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ConsumptionInfo getById(Long consumptionId) {
		return consumptionInfoRepository.findById(consumptionId).get();
	}

	@Override
	public List<ConsumptionInfo> getByRunId(Long runId) {
		return consumptionInfoRepository.findByProdRunHdrRunId(runId);
	}

	@Override
	public List<ConsumptionInfo> createAll(Long orderId, List<ConsumptionInfo> consumptionInfos) {
		ProdRunHdr prodRunHdr = prodRunHdrService.findLastProdRunHdrByOrderId(orderId);
		if(prodRunHdr == null || prodRunHdr.getStatus() != Status.ACT) {
			throw new EntityNotFoundException("Boyle bir siparis olusturulmamis veya baslatilmamistir!");
		}

		List<StockDto> stockDtos = new ArrayList<>();
		for (ConsumptionInfo consumptionInfo : consumptionInfos) {
			if(consumptionInfo.getConfirmationTime() == null) {
				throw new RuntimeException("Teyit tarihleri bos olamaz!");
			}
			consumptionInfo.setProdRunHdr(prodRunHdr);
			consumptionInfo.setUser(currentUserProvider.getCurrentUser().getUsername());
			StockDto stockDto = new StockDto();
			stockDto.setStockId(consumptionInfo.getStockId());
			stockDto.setQuantity(consumptionInfo.getQuantity());
			stockDtos.add(stockDto);
		}
		consumptionInfoRepository.saveAll(consumptionInfos);
		stockServiceClient.extractAllStock(stockDtos);
		return consumptionInfos;
	}

}


