package com.oee.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.PlantInfo;

@Repository
public interface PlantInfoRepository extends JpaRepository<PlantInfo, Long>{

	List<PlantInfo> findByClientClientId(Long clientId);

	List<PlantInfo> findByClientCompanyCompanyIdIn(Iterable<Long> ids);

}
