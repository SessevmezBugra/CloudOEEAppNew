package com.oee.service;

import java.util.List;

import com.oee.entity.ClientInfo;

public interface ClientInfoService {
	
	ClientInfo create(ClientInfo clientInfo);
	
	ClientInfo update(ClientInfo clientInfo);
	
	Boolean delete(Long clientId);
	
	ClientInfo getById(Long clientId);
	
	List<ClientInfo> getByCompanyId(Long companyId);

	List<ClientInfo> getClientsByLoggedUser();

	List<ClientInfo> getClientsByIds(List<Long> ids);

}
