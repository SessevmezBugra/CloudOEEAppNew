package com.oee.service.impl;

import java.util.Arrays;
import java.util.List;

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

import com.oee.entity.ProdRunData;
import com.oee.repository.ProdRunDataRepository;
import com.oee.service.ProdRunDataService;


@Service
@RequiredArgsConstructor
public class ProdRunDataServiceImpl implements ProdRunDataService{
	
	private final ProdRunDataRepository prodRunDataRepository;
	private final ProdRunHdrService prodRunHdrService;
	private final OrderServiceClient orderServiceClient;
	private final StockServiceClient stockServiceClient;
	private final CurrentUserProvider currentUserProvider;
	private final ModelMapper modelMapper;
	private final MainDataServiceClient mainDataServiceClient;

	@Override
	public ProdRunData create(ProdRunData prodRunData) {
		ProdRunHdr prodRunHdr = prodRunHdrService.findLastProdRunHdrByOrderId(prodRunData.getProdRunHdr().getOrderId());
		if(prodRunHdr == null || prodRunHdr.getStatus() != Status.ACT) {
			throw new EntityNotFoundException("Boyle bir siparis olusturulmamis veya baslatilmamistir.");
		}
		prodRunData.setProdRunHdr(prodRunHdr);
		prodRunData.setUser(currentUserProvider.getCurrentUser().getUsername());
		prodRunDataRepository.save(prodRunData);
		OrderedMaterialDto orderedMaterialDto = orderServiceClient.getOrderedMaterialByOrderId(prodRunHdr.getOrderId()).getBody();
		if (orderedMaterialDto.getIsStockProd()) {
			// stogu arttiracagiz malzeme ve depo bilgisini gondererek
			StockDto stockDto = new StockDto();
			stockDto.setMaterialId(orderedMaterialDto.getMaterialId());
			stockDto.setWarehouseId(orderedMaterialDto.getWarehouseId());
			stockDto.setQuantity(prodRunData.getQuantity());
			stockServiceClient.addStock(stockDto);
		}
		orderServiceClient.addProductionToActualProd(prodRunData.getProdRunHdr().getOrderId(), prodRunData.getQuantity());
		return prodRunData;
	}

	@Override
	public ProdRunData update(ProdRunData prodRunData) {
		return prodRunDataRepository.save(prodRunData);
	}

	@Override
	public Boolean delete(Long id) {
		prodRunDataRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ProdRunData getById(Long entryId) {
		return prodRunDataRepository.findById(entryId).get();
	}

	@Override
	public List<ProdRunDataDto> getByRunId(Long runId) {
		List<ProdRunData> prodRunDatas = prodRunDataRepository.findByProdRunHdrRunId(runId);
		List<ProdRunDataDto> prodRunDataDtos = Arrays.asList(modelMapper.map(prodRunDatas, ProdRunDataDto[].class));
		ProdRunHdr prodRunHdr = prodRunHdrService.getById(runId);
		OrderDto orderDto = orderServiceClient.getOrderInfoByOrderId(prodRunHdr.getOrderId()).getBody();
		List<QualityTypeDto> qualityTypeDtos = mainDataServiceClient.getQualityTypesByPlantId(orderDto.getPlantId()).getBody();
		for (ProdRunDataDto prodRunDataDto : prodRunDataDtos) {
			for (QualityTypeDto qualityTypeDto : qualityTypeDtos) {
				if (prodRunDataDto.getQualityId() == qualityTypeDto.getQualityId()) {
					prodRunDataDto.setQualityType(qualityTypeDto.getQualityType());
					break;
				}
			}
		}
		return prodRunDataDtos;
	}

	@Override
	public List<ProdRunData> createAll(Long orderId, List<ProdRunData> prodRunDatas) {
		ProdRunHdr prodRunHdr = prodRunHdrService.findLastProdRunHdrByOrderId(orderId);
		if(prodRunHdr == null || prodRunHdr.getStatus() != Status.ACT) {
			throw new EntityNotFoundException("Boyle bir siparis olusturulmamis veya baslatilmamistir.");
		}
		Double sumOfProduction = 0.0;
		for (ProdRunData prodRunData : prodRunDatas) {
			prodRunData.setProdRunHdr(prodRunHdr);
			prodRunData.setUser(currentUserProvider.getCurrentUser().getUsername());
			if(prodRunData.getScrap() == null){
				if(prodRunData.getQuantity() != null) {
					sumOfProduction += prodRunData.getQuantity();
				}
			}else {
				prodRunData.getScrap().setProdRunData(prodRunData);
			}
		}
		prodRunDataRepository.saveAll(prodRunDatas);
		OrderedMaterialDto orderedMaterialDto = orderServiceClient.getOrderedMaterialByOrderId(prodRunHdr.getOrderId()).getBody();
		if (orderedMaterialDto.getIsStockProd()) {
			// stogu arttiracagiz malzeme ve depo bilgisini gondererek
			StockDto stockDto = new StockDto();
			stockDto.setMaterialId(orderedMaterialDto.getMaterialId());
			stockDto.setWarehouseId(orderedMaterialDto.getWarehouseId());
			stockDto.setQuantity(sumOfProduction);
			stockServiceClient.addStock(stockDto);
		}
		orderServiceClient.addProductionToActualProd(orderId, sumOfProduction);
		return prodRunDatas;
	}

}
