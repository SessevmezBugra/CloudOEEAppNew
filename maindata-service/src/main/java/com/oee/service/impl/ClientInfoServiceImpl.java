package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.AuthServiceClient;
import com.oee.client.OrderServiceClient;
import com.oee.client.StockServiceClient;
import com.oee.dto.CurrentUser;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.entity.PlantInfo;
import com.oee.entity.WarehouseInfo;
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
	private final StockServiceClient stockServiceClient;
	private final OrderServiceClient orderServiceClient;
	
	public ClientInfoServiceImpl(ClientInfoRepository clientInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider, StockServiceClient stockServiceClient, OrderServiceClient orderServiceClient) {
		this.clientInfoRepository = clientInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
		this.stockServiceClient = stockServiceClient;
		this.orderServiceClient = orderServiceClient;
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
		ClientInfo clientInfo = clientInfoRepository.findById(clientId).get();
		List<PlantInfo> plants = clientInfo.getPlants();
		List<Long> plantIds = new ArrayList<>();
		List<Long> warehouseIds = new ArrayList<>();
		List<Long> clientAndPlantIds = new ArrayList<>();
		clientAndPlantIds.add(clientInfo.getClientId());
		for (PlantInfo plant : plants) {
			clientAndPlantIds.add(plant.getPlantId());
				List<WarehouseInfo> warehouses = plant.getWarehouses();
				for (WarehouseInfo warehouse : warehouses) {
					warehouseIds.add(warehouse.getWarehouseId());
				}
		}
		clientInfoRepository.deleteById(clientId);
		orderServiceClient.deleteOrderByPlantIds(plantIds);
		stockServiceClient.deleteStockByWarehouseIds(warehouseIds);
		authServiceClient.deleteResponsibleAreaByIds(clientAndPlantIds);
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
