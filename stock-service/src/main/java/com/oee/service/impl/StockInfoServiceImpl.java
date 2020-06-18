package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.StockInfo;
import com.oee.repository.StockRepository;
import com.oee.service.StockInfoService;

@Service
public class StockInfoServiceImpl implements StockInfoService{
	
	private final StockRepository stockRepository;
	
	public StockInfoServiceImpl(StockRepository stockRepository) {
		this.stockRepository = stockRepository;
	}

	@Override
	public StockInfo create(StockInfo stockInfo) {
		return stockRepository.save(stockInfo);
	}

	@Override
	public StockInfo update(StockInfo stockInfo) {
		return stockRepository.save(stockInfo);
	}

	@Override
	public Boolean delete(Long id) {
		stockRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public StockInfo getById(Long id) {
		return stockRepository.findById(id).get();
	}

	@Override
	public List<StockInfo> getByWarehouseId(Long warehouseId) {
		return stockRepository.findByWarehouseId(warehouseId);
	}

	@Override
	public StockInfo addStock(StockInfo stockInfo) {
		if(stockInfo.getMaterialId() == null || stockInfo.getWarehouseId() == null) {
			throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
		}
		System.err.println(stockInfo.getMaterialId()+ " " + stockInfo.getWarehouseId());
		//O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
		StockInfo foundStockInfo = stockRepository.findByMaterialIdAndWarehouseId(stockInfo.getMaterialId(), stockInfo.getWarehouseId());
		if (foundStockInfo != null) {
			System.err.println("test1");
			foundStockInfo.setQuantity(foundStockInfo.getQuantity() + stockInfo.getQuantity());
			System.err.println(foundStockInfo.getQuantity());
			stockRepository.save(foundStockInfo);
			return foundStockInfo;
		}else {
			System.err.println("test32");
			stockRepository.save(stockInfo);
			return stockInfo;
		}
	}

	@Override
	public StockInfo extractStock(StockInfo stockInfo) {
		// TODO Auto-generated method stub
		if(stockInfo.getMaterialId() == null || stockInfo.getWarehouseId() == null) {
			throw new RuntimeException("MaterialId veya WarehouseId bos olamaz!");
		}
		//O malzeme o depoda var mi yok mu kontrolu yapilir. Varsa uzerine belirtilen miktar eklenir. Yoksa stok olusturulur.
		StockInfo foundStockInfo = stockRepository.findByMaterialIdAndWarehouseId(stockInfo.getMaterialId(), stockInfo.getWarehouseId());
		if (foundStockInfo != null) {
			
			if (foundStockInfo.getQuantity() < stockInfo.getQuantity()) {
				throw new RuntimeException("Depoda bu miktarda malzeme bulunmamaktadir.");
			}
			foundStockInfo.setQuantity(foundStockInfo.getQuantity() - stockInfo.getQuantity());
			stockRepository.save(foundStockInfo);
			return foundStockInfo;
		}else {
			throw new RuntimeException("Depoda bu malzeme bulunmamaktadir.");
		}
	}

}
