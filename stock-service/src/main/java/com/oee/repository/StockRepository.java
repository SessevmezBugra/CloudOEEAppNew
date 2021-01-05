package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.oee.entity.StockInfo;

import javax.transaction.Transactional;

public interface StockRepository extends JpaRepository<StockInfo, Long>{
	
	List<StockInfo> findByWarehouseId(Long warehouseId);
	
	StockInfo findByMaterialIdAndWarehouseId(Long materialId, Long warehouseId);

	List<StockInfo> findByWarehouseIdIn(Iterable<Long> warehouseIds);

	@Transactional
    void deleteByWarehouseIdIn(List<Long> warehouseIds);

//    @Modifying
//	@Query("UPDATE StockInfo s SET s.quantity = s.quantity + :quantity WHERE s.stockId")
//	void addStock(@Param("quantity") Double quantity, @Param("stockId") Long stockId);
//
//    @Modifying
//	@Query("UPDATE StockInfo s SET s.quantity = s.quantity - :quantity WHERE s.stockId")
//	void extractStock(@Param("quantity") Double quantity, @Param("stockId") Long stockId);
		
}
