package com.oee.service.impl;

import java.util.List;

import com.oee.client.OrderServiceClient;
import com.oee.entity.ProdRunHdr;
import com.oee.enums.Status;
import com.oee.error.EntityNotFoundException;
import com.oee.service.ProdRunHdrService;
import lombok.RequiredArgsConstructor;
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

	@Override
	public ProdRunData create(ProdRunData prodRunData) {
		ProdRunHdr prodRunHdr = prodRunHdrService.findLastProdRunHdrByOrderId(prodRunData.getProdRunHdr().getOrderId());
		if(prodRunHdr == null || prodRunHdr.getStatus() != Status.ACT) {
			throw new EntityNotFoundException("Boyle bir siparis olusturulmamis veya baslatilmamistir.");
		}
		prodRunData.setProdRunHdr(prodRunHdr);
		prodRunDataRepository.save(prodRunData);
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
	public List<ProdRunData> getByRunId(Long runId) {
		return prodRunDataRepository.findByProdRunHdrRunId(runId);
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
			if(!prodRunData.getQualityType().equals("SCRAP")){
				if(prodRunData.getQuantity() != null) {
					sumOfProduction += prodRunData.getQuantity();
				}
			}
		}
		prodRunDataRepository.saveAll(prodRunDatas);
		orderServiceClient.addProductionToActualProd(orderId, sumOfProduction);
		return prodRunDatas;
	}

}
