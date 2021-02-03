package com.oee.service.impl;

import com.oee.entity.ReasonCodeEntity;
import com.oee.error.EntityNotFoundException;
import com.oee.repository.ReasonCodeRepository;
import com.oee.service.ReasonCodeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReasonCodeServiceImpl implements ReasonCodeService {

    private final ReasonCodeRepository reasonCodeRepository;

    @Override
    public ReasonCodeEntity create(ReasonCodeEntity reasonCodeEntity) {
        return reasonCodeRepository.save(reasonCodeEntity);
    }

    @Override
    public ReasonCodeEntity update(ReasonCodeEntity reasonCodeEntity) {
        ReasonCodeEntity code = reasonCodeRepository.findById(reasonCodeEntity.getId()).get();
        code.setCode(reasonCodeEntity.getCode());
        code.setDesc(reasonCodeEntity.getDesc());
        code.setType(reasonCodeEntity.getType());
        return reasonCodeRepository.save(code);
    }

    @Override
    public Boolean deleteById(Long id) {
        reasonCodeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ReasonCodeEntity findById(Long id) {
        return reasonCodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boyle bir neden kodu bulunmamaktadir."));
    }

    @Override
    public List<ReasonCodeEntity> findAll() {
        return reasonCodeRepository.findAll();
    }

    @Override
    public List<ReasonCodeEntity> findByPlantId(Long id) {
        return reasonCodeRepository.findByPlantId(id);
    }

}
