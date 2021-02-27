package com.oee.service.impl;

import com.oee.entity.QualityTypeEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.QualityTypeRepository;
import com.oee.service.QualityTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class QualityTypeServiceImpl implements QualityTypeService {

    private final QualityTypeRepository repository;

    @Override
    public QualityTypeEntity create(QualityTypeEntity qualityTypeEntity) {
        return repository.save(qualityTypeEntity);
    }

    @Override
    public QualityTypeEntity update(QualityTypeEntity qualityTypeEntity) {
        QualityTypeEntity type = repository.findById(qualityTypeEntity.getId()).get();

        return repository.save(type);
    }

    @Override
    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    @Override
    public QualityTypeEntity findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boyle bir kalite tipi bulunmamaktadir."));
    }

    @Override
    public List<QualityTypeEntity> findAll() {
        return repository.findAll();
    }

    @Override
    public List<QualityTypeEntity> findByPlantId(Long id) {
        return repository.findByPlantId(id);
    }

}
