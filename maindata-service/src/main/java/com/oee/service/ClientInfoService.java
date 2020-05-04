package com.oee.service;

import java.util.List;

import com.oee.entity.ClientInfo;

public interface ClientInfoService {
	
	ClientInfo create(ClientInfo clientInfo);
	
	ClientInfo update(ClientInfo clientInfo);
	
	Boolean delete(Integer clientId);
	
	ClientInfo getById(Integer clientId);
	
	List<ClientInfo> getByCompanyId(Integer companyId);
}
