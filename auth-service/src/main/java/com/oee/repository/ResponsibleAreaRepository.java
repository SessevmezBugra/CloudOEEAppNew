package com.oee.repository;

import java.util.List;

import com.oee.enums.UserGroup;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.oee.entity.ResponsibleArea;

import javax.transaction.Transactional;

@Repository
public interface ResponsibleAreaRepository extends JpaRepository<ResponsibleArea, Long>{
	
//	List<ResponsibleArea> findByUserEntityId(String userId);

//	@Transactional
//    void deleteByAreaIdIn(List<Long> areaIds);
//
//    @Transactional
//    void deleteByUserEntityId(String userId);

//    ResponsibleArea findByUserEntityIdAndUserRoleAndAreaId(String userId, UserGroup role, Long areaId);
}
