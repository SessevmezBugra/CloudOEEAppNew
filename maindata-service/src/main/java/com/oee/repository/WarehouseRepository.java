package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.WarehouseInfo;

@Repository
public interface WarehouseRepository extends JpaRepository<WarehouseInfo, Long>{

	List<WarehouseInfo> findByPlantPlantId(Long plantId);

	List<WarehouseInfo> findByPlantClientCompanyCompanyIdIn(Iterable<Long> ids);
}
