package com.oee.service.impl;

import com.oee.client.AuthServiceClient;
import com.oee.dto.ResponsibleAreaDto;
import com.oee.dto.UserEntityDto;
import org.springframework.stereotype.Service;

import com.oee.entity.CompanyInfo;
import com.oee.repository.CompanyInfoRepository;
import com.oee.service.CompanyInfoService;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class CompanyInfoServiceImpl implements CompanyInfoService{

	private final CompanyInfoRepository companyInfoRepository;
	private final AuthServiceClient authServiceClient;
	private final CurrentUserProvider currentUserProvider;
	
	public CompanyInfoServiceImpl(CompanyInfoRepository companyInfoRepository, AuthServiceClient authServiceClient, CurrentUserProvider currentUserProvider) {
		this.companyInfoRepository = companyInfoRepository;
		this.authServiceClient = authServiceClient;
		this.currentUserProvider = currentUserProvider;
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
		companyInfoRepository.deleteById(companyId);
		return Boolean.TRUE;
	}

	@Override
	public CompanyInfo getById(Long companyId) {
		return companyInfoRepository.findById(companyId).get();
	}

}
