package com.oee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.client.OrderServiceClient;
import com.oee.client.StockServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.dto.*;
import com.oee.entity.ProdRunHdr;
import com.oee.enums.Status;
import com.oee.error.EntityNotFoundException;
import com.oee.service.ProdRunHdrService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
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
	private final ModelMapper modelMapper;
	private final OrderServiceClient orderServiceClient;
	private final MainDataServiceClient mainDataServiceClient;

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
	public List<ConsumptionDataDto> getByRunId(Long runId) {
		List<ConsumptionInfo> consumptionInfos = consumptionInfoRepository.findByProdRunHdrRunId(runId);
		List<ConsumptionDataDto> consumptionDataDtos = Arrays.asList(modelMapper.map(consumptionInfos, ConsumptionDataDto[].class));
		ProdRunHdr prodRunHdr = prodRunHdrService.getById(runId);
		OrderDto orderDto = orderServiceClient.getOrderInfoByOrderId(prodRunHdr.getOrderId()).getBody();
		List<Long> stockIds = consumptionDataDtos.stream().map(ConsumptionDataDto::getStockId).collect(Collectors.toList());
		List<StockDto> stockDtos = stockServiceClient.getStocksByStockIds(stockIds).getBody();
		List<MaterialDto> materialDtos = mainDataServiceClient.getMaterialsByPlantId(orderDto.getPlantId()).getBody();
		List<WarehouseDto> warehouseDtos = mainDataServiceClient.getWarehousesByPlantId(orderDto.getPlantId()).getBody();
		for (ConsumptionDataDto consumptionDataDto : consumptionDataDtos) {
			for (StockDto stockDto : stockDtos) {
				if (consumptionDataDto.getStockId() == stockDto.getStockId()) {
					consumptionDataDto.setMaterialId(stockDto.getMaterialId());
					consumptionDataDto.setWarehouseId(stockDto.getWarehouseId());
					break;
				}
			}
			for (MaterialDto materialDto : materialDtos) {
				if (consumptionDataDto.getMaterialId() == materialDto.getMaterialId()) {
					consumptionDataDto.setMaterialDesc(materialDto.getMaterialDesc());
					break;
				}
			}
			for (WarehouseDto warehouseDto : warehouseDtos) {
				if (consumptionDataDto.getWarehouseId() == warehouseDto.getWarehouseId()) {
					consumptionDataDto.setWarehouseName(warehouseDto.getWarehouseName());
					break;
				}
			}

		}
		return consumptionDataDtos;
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


