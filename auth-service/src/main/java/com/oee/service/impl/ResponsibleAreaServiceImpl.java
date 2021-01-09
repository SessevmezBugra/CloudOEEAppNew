package com.oee.service.impl;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.dto.ClientDto;
import com.oee.dto.CompanyDto;
import com.oee.dto.PlantDto;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import lombok.RequiredArgsConstructor;
import org.apache.catalina.User;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.oee.entity.ResponsibleArea;
import com.oee.repository.ResponsibleAreaRepository;
import com.oee.service.ResponsibleAreaService;

@Service
@RequiredArgsConstructor
public class ResponsibleAreaServiceImpl implements ResponsibleAreaService{

	private final ResponsibleAreaRepository responsibleAreaRepository;
	private final CurrentUserProvider currentUserProvider;
	private final ModelMapper modelMapper;
	private final MainDataServiceClient mainDataServiceClient;

	@Override
	public ResponsibleArea create(ResponsibleArea responsibleArea) {
		return responsibleAreaRepository.save(responsibleArea);
	}

	@Override
	public ResponsibleArea update(ResponsibleArea responsibleArea) {
		return responsibleAreaRepository.save(responsibleArea);
	}

	@Override
	public Boolean delete(Long id) {
		responsibleAreaRepository.deleteById(id);
		return Boolean.TRUE;
	}

	@Override
	public Boolean deleteByUserId(String userId) {
		responsibleAreaRepository.deleteByUserEntityId(userId);
		return Boolean.TRUE;
	}

	@Override
	public ResponsibleArea findById(Long id) {
		return responsibleAreaRepository.findById(id).get();
	}

	@Override
	public List<ResponsibleArea> findByUserId() {
		return responsibleAreaRepository.findByUserEntityId(currentUserProvider.getCurrentUser().getUserId());
	}

	@Override
	public Boolean deleteByAreaIds(List<Long> areaIds) {
		responsibleAreaRepository.deleteByAreaIdIn(areaIds);
		return Boolean.TRUE;
	}

	@Override
	public List<ResponsibleAreaDto> findByUserId(String userId) {
		List<ResponsibleArea> responsibleAreas = responsibleAreaRepository.findByUserEntityId(userId);
		List<ResponsibleAreaDto> responsibleAreaDtos = Arrays.asList(modelMapper.map(responsibleAreas, ResponsibleAreaDto[].class));
		List<Long> companyIds = responsibleAreaDtos.stream().filter(responsibleAreaDto -> responsibleAreaDto.getAreaType().equals(AreaType.COMPANY)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
		List<Long> clientIds = responsibleAreaDtos.stream().filter(responsibleAreaDto -> responsibleAreaDto.getAreaType().equals(AreaType.CLIENT)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
		List<Long> plantIds = responsibleAreaDtos.stream().filter(responsibleAreaDto -> responsibleAreaDto.getAreaType().equals(AreaType.PLANT)).map(ResponsibleAreaDto::getAreaId).collect(Collectors.toList());
		List<CompanyDto> companyDtos = new ArrayList<>();
		List<ClientDto> clientDtos = new ArrayList<>();
		List<PlantDto> plantDtos = new ArrayList<>();
		if (companyIds != null && companyIds.size() > 0) {
			 companyDtos = mainDataServiceClient.getCompaniesByIds(companyIds).getBody();
		}

		if (clientIds != null && clientIds.size() > 0) {
			clientDtos = mainDataServiceClient.getClientsByIds(clientIds).getBody();
		}

		if (plantIds != null && plantIds.size() > 0) {
			plantDtos = mainDataServiceClient.getPlantsByIds(plantIds).getBody();
		}
		for (ResponsibleAreaDto responsibleAreaDto : responsibleAreaDtos) {
			System.err.println(responsibleAreaDto.getAreaType().name());
			if (responsibleAreaDto.getAreaType().name().equals(AreaType.COMPANY.name())) {
				for (CompanyDto companyDto : companyDtos) {
					if (responsibleAreaDto.getAreaId() == companyDto.getCompanyId()) {
						responsibleAreaDto.setAreaName(companyDto.getCompanyName());
						break;
					}
				}
			}else if (responsibleAreaDto.getAreaType().name().equals(AreaType.CLIENT.name())) {
				for (ClientDto clientDto : clientDtos) {
					if (responsibleAreaDto.getAreaId() == clientDto.getClientId()) {
						responsibleAreaDto.setAreaName(clientDto.getClientName());
						break;
					}
				}
			}else if(responsibleAreaDto.getAreaType().name().equals(AreaType.PLANT.name())) {
				System.err.println("Planta girdi");
				for (PlantDto plantDto : plantDtos) {
					System.err.println("Plant Id: " + plantDto.getPlantId());
					System.err.println("areadan Id: " + responsibleAreaDto.getAreaId());
					if (responsibleAreaDto.getAreaId() == plantDto.getPlantId()) {
						System.err.println("Planta girdi 2");
						responsibleAreaDto.setAreaName(plantDto.getPlantName());
						break;
					}
				}
			}
		}
		return responsibleAreaDtos;
	}

	@Override
	public ResponsibleArea findByUserIdAndUserRoleAndAreaId(String userId, UserRole role, Long areaId) {
		return responsibleAreaRepository.findByUserEntityIdAndUserRoleAndAreaId(userId, role, areaId);
	}


}
