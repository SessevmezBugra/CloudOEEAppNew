package com.oee.service;

import java.util.List;

import com.oee.dto.ResponsibleAreaDto;
import com.oee.entity.ResponsibleArea;
import com.oee.enums.UserGroup;

public interface ResponsibleAreaService {
	
	ResponsibleArea create(ResponsibleArea responsibleArea);
	
	ResponsibleArea update(ResponsibleArea responsibleArea);
	
	Boolean delete(Long id);

	Boolean deleteByUserId(String userId);
	
	ResponsibleArea findById(Long id);
	
	List<ResponsibleArea> findByUserId();

    Boolean deleteByAreaIds(List<Long> areaIds);

    List<ResponsibleAreaDto> findByUserId(String userId);

    ResponsibleArea findByUserIdAndUserRoleAndAreaId(String userId, UserGroup role, Long areaId);
}
