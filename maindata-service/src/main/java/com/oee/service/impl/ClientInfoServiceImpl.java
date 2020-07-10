package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import org.springframework.stereotype.Service;

import com.oee.entity.ClientInfo;
import com.oee.repository.ClientInfoRepository;
import com.oee.service.ClientInfoService;

@Service
public class ClientInfoServiceImpl implements ClientInfoService{
	
	private final ClientInfoRepository clientInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	
	public ClientInfoServiceImpl(ClientInfoRepository clientInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider) {
		this.clientInfoRepository = clientInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
	}

	@Override
	public ClientInfo create(ClientInfo clientInfo) {
		System.err.println(clientInfo.getCompany().getCompanyId());
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

	@Override
	public List<ClientInfo> getClientsByLoggedUser() {
		CurrentUser currentUser = currentUserProvider.getCurrentUser();
		Boolean isCompanyOwner = currentUser.hasRole(UserRole.COMPANY_OWNER.getRole());
		Boolean isClientManager = currentUser.hasRole(UserRole.CLIENT_MANAGER.getRole());
		List<ClientInfo> clientInfos = new ArrayList<>();
		if(isCompanyOwner) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.COMPANY.name())).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			clientInfos = clientInfoRepository.findByCompanyCompanyIdIn(ids);
			return clientInfos;
		} else if(isClientManager) {
			List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
			//Daha sonra bu streame area type filtresi eklenmeli.
			List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals(AreaType.CLIENT.name())).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
			clientInfos =  clientInfoRepository.findAllById(ids);
			return clientInfos;
		}
		return clientInfos;
	}

}
