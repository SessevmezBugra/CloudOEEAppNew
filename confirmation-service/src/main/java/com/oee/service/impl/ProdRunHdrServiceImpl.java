package com.oee.service.impl;

import java.util.List;

import com.oee.config.CurrentUserProvider;
import com.oee.enums.OrderStatus;
import org.springframework.stereotype.Service;

import com.oee.entity.ProdRunHdr;
import com.oee.repository.ProdRunHdrRepository;
import com.oee.service.ProdRunHdrService;

@Service
public class ProdRunHdrServiceImpl implements ProdRunHdrService{

	private final ProdRunHdrRepository prodRunHdrRepository;
	private final CurrentUserProvider currentUserProvider;
	
	public ProdRunHdrServiceImpl(ProdRunHdrRepository prodRunHdrRepository, CurrentUserProvider currentUserProvider) {
		this.prodRunHdrRepository = prodRunHdrRepository;
		this.currentUserProvider = currentUserProvider;
	}
	
	@Override
	public ProdRunHdr create(ProdRunHdr prodRunHdr) {
//		List<ProdRunHdr> prodRunHdrs = prodRunHdrRepository.findByOrderId(prodRunHdr.getOrderId());
//		if (prodRunHdrs != null || prodRunHdrs.size() > 0) {
//			throw new RuntimeException("Bu siparis daha once olusturulmustur.");
//		}
//		prodRunHdr.setStatus(Status.ACT);
//		prodRunHdr.setStartedUser(currentUserProvider.getCurrentUser().getUsername());
		return prodRunHdrRepository.save(prodRunHdr);
	}

	@Override
	public ProdRunHdr update(ProdRunHdr prodRunHdr) {
		return prodRunHdrRepository.save(prodRunHdr);
	}

	@Override
	public Boolean delete(Long id) {
		prodRunHdrRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public ProdRunHdr getById(Long runId) {
		return prodRunHdrRepository.findById(runId).get();
	}

	@Override
	public List<ProdRunHdr> getByOrderId(Long orderId) {
		return prodRunHdrRepository.findByOrderId(orderId);
	}

	@Override
	public ProdRunHdr start(ProdRunHdr prodRunHdrDto) {
		ProdRunHdr lastProdRunHdr = prodRunHdrRepository.findTopByOrderIdOrderByRunIdDesc(prodRunHdrDto.getOrderId());
		if (lastProdRunHdr != null && lastProdRunHdr.getOrderStatus() != OrderStatus.HOLD) {
			throw new RuntimeException("Bu siparisin durumu baslatmak icin uygun degildir.");
		}
		ProdRunHdr newProdRunHdr = new ProdRunHdr();
		newProdRunHdr.setOrderStatus(OrderStatus.ACT);
		newProdRunHdr.setStartedUser(currentUserProvider.getCurrentUser().getUsername());
		newProdRunHdr.setStartTime(prodRunHdrDto.getStartTime());
		newProdRunHdr.setOrderId(prodRunHdrDto.getOrderId());
		return prodRunHdrRepository.save(newProdRunHdr);
	}

	@Override
	public ProdRunHdr hold(ProdRunHdr prodRunHdrDto) {
		ProdRunHdr lastProdRunHdr = prodRunHdrRepository.findTopByOrderIdOrderByRunIdDesc(prodRunHdrDto.getOrderId());
		if (lastProdRunHdr == null || lastProdRunHdr.getOrderStatus() != OrderStatus.ACT) {
			throw new RuntimeException("Bu siparisin durumu baslatmak icin uygun degildir.");
		}
		lastProdRunHdr.setOrderStatus(OrderStatus.HOLD);
		lastProdRunHdr.setEndingUser(currentUserProvider.getCurrentUser().getUsername());
		lastProdRunHdr.setEndTime(prodRunHdrDto.getEndTime());
		return prodRunHdrRepository.save(lastProdRunHdr);
	}

	@Override
	public ProdRunHdr complete(ProdRunHdr prodRunHdrDto) {
		ProdRunHdr lastProdRunHdr = prodRunHdrRepository.findTopByOrderIdOrderByRunIdDesc(prodRunHdrDto.getOrderId());
		if (lastProdRunHdr == null || lastProdRunHdr.getOrderStatus() != OrderStatus.ACT) {
			throw new RuntimeException("Bu siparisi tamamlamak icin baslatmaniz gerekmektedir.");
		}
		lastProdRunHdr.setOrderStatus(OrderStatus.CMPL);
		lastProdRunHdr.setEndingUser(currentUserProvider.getCurrentUser().getUsername());
		lastProdRunHdr.setEndTime(prodRunHdrDto.getEndTime());
		return prodRunHdrRepository.save(lastProdRunHdr);
	}

	@Override
	public Boolean deleteByOrderIds(List<Long> orderIds) {
		prodRunHdrRepository.deleteByOrderIdIn(orderIds);
		return Boolean.TRUE;
	}

	@Override
	public ProdRunHdr findLastProdRunHdrByOrderId(Long orderId) {
		return prodRunHdrRepository.findTopByOrderIdOrderByRunIdDesc(orderId);
	}

}
