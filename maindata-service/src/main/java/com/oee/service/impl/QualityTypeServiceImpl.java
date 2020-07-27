package com.oee.service.impl;

import com.oee.entity.QualityType;
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
    public QualityType create(QualityType qualityType) {
        return repository.save(qualityType);
    }

    @Override
    public QualityType update(QualityType qualityType) {
        return repository.save(qualityType);
    }

    @Override
    public Boolean deleteById(Long id) {
        repository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public QualityType findById(Long id) {
        return repository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boyle bir kalite tipi bulunmamaktadir."));
    }

    @Override
    public List<QualityType> findAll() {
        return repository.findAll();
    }

    @Override
    public List<QualityType> findByPlantId(Long id) {
        return repository.findByPlantId(id);
    }

}
