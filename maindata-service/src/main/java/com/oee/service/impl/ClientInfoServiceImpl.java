package com.oee.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;

import com.oee.entity.ClientInfo;
import com.oee.repository.ClientInfoRepository;
import com.oee.service.ClientInfoService;

@Service
public class ClientInfoServiceImpl implements ClientInfoService{
	
	private final ClientInfoRepository clientInfoRepository;
	
	public ClientInfoServiceImpl(ClientInfoRepository clientInfoRepository) {
		this.clientInfoRepository = clientInfoRepository;
	}

	@Override
	public ClientInfo create(ClientInfo clientInfo) {
		return clientInfoRepository.save(clientInfo);
	}

	@Override
	public ClientInfo update(ClientInfo clientInfo) {
		return clientInfoRepository.save(clientInfo);
	}

	@Override
	public Boolean delete(Long clientId) {
		clientInfoRepository.deleteById(clientId);
		return Boolean.TRUE;
	}

	@Override
	public ClientInfo getById(Long clientId) {
		return clientInfoRepository.findById(clientId).get();
	}

	@Override
	public List<ClientInfo> getByCompanyId(Long companyId) {
		return clientInfoRepository.findByCompanyCompanyId(companyId);
	}

}
