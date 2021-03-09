package com.oee.repository;

import java.util.List;

import com.oee.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import javax.transaction.Transactional;

public interface StockRepository extends JpaRepository<Stock, Long>{
	
	List<Stock> findByWarehouseId(Long warehouseId);
	
	Stock findByMaterialIdAndWarehouseId(Long materialId, Long warehouseId);

	List<Stock> findByWarehouseIdIn(Iterable<Long> warehouseIds);

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
