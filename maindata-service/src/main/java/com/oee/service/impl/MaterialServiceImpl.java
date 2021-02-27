package com.oee.service.impl;

import java.util.List;

import com.oee.client.AuthServiceClient;
import com.oee.entity.MaterialEntity;
import com.oee.error.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import com.oee.repository.MaterialRepository;
import com.oee.service.MaterialService;

@Service
@Slf4j
@RequiredArgsConstructor
public class MaterialServiceImpl implements MaterialService {
	
	private final MaterialRepository materialRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;

	@Override
	public MaterialEntity create(MaterialEntity materialEntity) {
		return materialRepository.save(materialEntity);
	}

	@Override
	public MaterialEntity update(MaterialEntity materialEntityInfo) {
		MaterialEntity materialEntity = materialRepository.findById(materialEntityInfo.getId()).get();
		materialEntity.setDesc(materialEntityInfo.getDesc());
		materialEntity.setNumber(materialEntityInfo.getNumber());
		return materialRepository.save(materialEntity);
	}

	@Override
	public void deleteById(Long materialId) {
		if (materialId == null) {
			log.error("Malzeme silinirken id bos geldi.");
			throw new IllegalArgumentException("Malzeme id bos olamaz.");
		}
		materialRepository.deleteById(materialId);
	}

	@Override
	public MaterialEntity findById(Long materialId) {
		return materialRepository.findById(materialId).orElseThrow(() -> new EntityNotFoundException("Bu malzeme bulunamadi."));
	}

	@Override
	public List<MaterialEntity> findMaterialsByIds(List<Long> ids) {
		return materialRepository.findAllById(ids);
	}

}
