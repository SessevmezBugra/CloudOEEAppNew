package com.oee.service.impl;

import com.oee.client.AuthServiceClient;
import com.oee.client.OrderServiceClient;
import com.oee.client.StockServiceClient;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.dto.UserEntityDto;
import com.oee.entity.ClientInfo;
import com.oee.entity.PlantInfo;
import com.oee.entity.WarehouseInfo;
import com.oee.error.EntityNotFoundException;
import org.springframework.stereotype.Service;

import com.oee.entity.CompanyInfo;
import com.oee.repository.CompanyInfoRepository;
import com.oee.service.CompanyInfoService;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService{

	private final CompanyInfoRepository companyInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	private final StockServiceClient stockServiceClient;
	private final OrderServiceClient orderServiceClient;
	
	public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider, StockServiceClient stockServiceClient, OrderServiceClient orderServiceClient) {
		this.companyInfoRepository = companyInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
		this.stockServiceClient = stockServiceClient;
		this.orderServiceClient = orderServiceClient;
	}

	@Override
	public List<CompanyInfo> findCompanies() {
		List<ResponsibleAreaDto> areaDtos = authServiceClient.getResponsibleArea().getBody();
		//Daha sonra bu streame area type filtresi eklenmeli.
		List<Long> ids = areaDtos.stream().filter(rad -> rad.getAreaType().equals("COMPANY")).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
		return companyInfoRepository.findAllById(ids);
	}

	@Override
	public CompanyInfo create(CompanyInfo companyInfo) {
		Boolean isCompanyOwner = currentUserProvider.getCurrentUser().getRoles().contains("ROLE_COMPANY_OWNER");
		if (!isCompanyOwner){
			authServiceClient.addCompanyOwnerRole();
		}
		companyInfoRepository.save(companyInfo);
		ResponsibleAreaDto responsibleAreaDto = new ResponsibleAreaDto();
		responsibleAreaDto.setAreaId(companyInfo.getCompanyId());
		responsibleAreaDto.setAreaType("COMPANY");
		UserEntityDto userEntityDto = new UserEntityDto();
		userEntityDto.setId(currentUserProvider.getCurrentUser().getUserId());
		responsibleAreaDto.setUserEntity(userEntityDto);
		authServiceClient.addCompanyToResponsibleArea(responsibleAreaDto);

		return companyInfo;
	}

	@Override
	public CompanyInfo update(CompanyInfo companyInfo) {
		return companyInfoRepository.save(companyInfo);
	}

	@Override
	public Boolean delete(Long companyId) {
		CompanyInfo companyInfo = companyInfoRepository.findById(companyId).get();
		List<ClientInfo> clients = companyInfo.getClients();
		List<Long> plantIds = new ArrayList<>();
		List<Long> warehouseIds = new ArrayList<>();
		List<Long> companyAndClientAndPlantIds = new ArrayList<>();
		companyAndClientAndPlantIds.add(companyInfo.getCompanyId());
		for (ClientInfo client : clients) {
			companyAndClientAndPlantIds.add(client.getClientId());
			List<PlantInfo> plants = client.getPlants();
			for (PlantInfo plant : plants) {
				companyAndClientAndPlantIds.add(plant.getPlantId());
				plantIds.add(plant.getPlantId());
				List<WarehouseInfo> warehouses = plant.getWarehouses();
				for (WarehouseInfo warehouse : warehouses) {
					warehouseIds.add(warehouse.getWarehouseId());
				}
			}
		}

		companyInfoRepository.deleteById(companyId);
		orderServiceClient.deleteOrderByPlantIds(plantIds);
		stockServiceClient.deleteStockByWarehouseIds(warehouseIds);
		authServiceClient.deleteResponsibleAreaByIds(companyAndClientAndPlantIds);
		return Boolean.TRUE;
	}

	@Override
	public CompanyInfo getById(Long companyId) {
		return companyInfoRepository.findById(companyId).orElseThrow(() -> new EntityNotFoundException("Boyle bir sirket bulunmamaktadir."));
	}

}
