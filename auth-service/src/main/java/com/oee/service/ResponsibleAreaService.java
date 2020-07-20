package com.oee.service;

import java.util.List;

import com.oee.entity.ResponsibleArea;

public interface ResponsibleAreaService {
	
	ResponsibleArea create(ResponsibleArea responsibleArea);
	
	ResponsibleArea update(ResponsibleArea responsibleArea);
	
	Boolean delete(Long id);
	
	ResponsibleArea findById(Long id);
	
	List<ResponsibleArea> findByUserId();

    Boolean deleteByAreaIds(List<Long> areaIds);
}
