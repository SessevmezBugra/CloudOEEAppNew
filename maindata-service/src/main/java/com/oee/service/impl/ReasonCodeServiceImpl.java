package com.oee.service.impl;

import com.oee.entity.ReasonCode;
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
    public ReasonCode create(ReasonCode reasonCode) {
        return reasonCodeRepository.save(reasonCode);
    }

    @Override
    public ReasonCode update(ReasonCode reasonCode) {
        ReasonCode code = reasonCodeRepository.findById(reasonCode.getId()).get();
        code.setCode(reasonCode.getCode());
        code.setDesc(reasonCode.getDesc());
        code.setType(reasonCode.getType());
        return reasonCodeRepository.save(code);
    }

    @Override
    public Boolean deleteById(Long id) {
        reasonCodeRepository.deleteById(id);
        return Boolean.TRUE;
    }

    @Override
    public ReasonCode findById(Long id) {
        return reasonCodeRepository.findById(id).orElseThrow(() -> new EntityNotFoundException("Boyle bir neden kodu bulunmamaktadir."));
    }

    @Override
    public List<ReasonCode> findAll() {
        return reasonCodeRepository.findAll();
    }

    @Override
    public List<ReasonCode> findByPlantId(Long id) {
        return reasonCodeRepository.findByPlantId(id);
    }

}
