package com.oee.web;

import java.security.Principal;
import java.util.List;
import java.util.Set;

import org.keycloak.KeycloakPrincipal;
import org.keycloak.KeycloakSecurityContext;
import org.keycloak.adapters.springsecurity.token.KeycloakAuthenticationToken;
import org.keycloak.representations.AccessToken;
import org.keycloak.representations.AccessToken.Access;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.oee.dto.StockInfoDto;
import com.oee.entity.CompanyInfo;
import com.oee.service.CompanyInfoService;
import com.oee.service.StockServiceProxy;
import com.oee.util.ApiPaths;

import javax.annotation.security.RolesAllowed;

@RestController
@RequestMapping(ApiPaths.CompanyInfoCtrl.CTRL)
public class CompanyInfoRestController {

	private final Environment environment;
	private final CompanyInfoService companyInfoService;

	public CompanyInfoRestController(Environment environment, CompanyInfoService companyInfoService) {
		this.environment = environment;
		this.companyInfoService = companyInfoService;
	}

	@RolesAllowed("COMPANY_OWNER")
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<List<CompanyInfo>> getCompanies() {
		return ResponseEntity.ok(companyInfoService.findCompanies());
	}

//	@RolesAllowed("user")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyInfo> createCompanyInfo(@RequestBody CompanyInfo companyInfo) {
		return ResponseEntity.ok(companyInfoService.create(companyInfo));
	}

	@RequestMapping(method = RequestMethod.PUT)
	public ResponseEntity<CompanyInfo> updateCompanyInfo(@RequestBody CompanyInfo companyInfo) {
		return ResponseEntity.ok(companyInfoService.update(companyInfo));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<Boolean> deleteCompanyInfo(@PathVariable(value = "id", required = true) Long companyId) {
		return ResponseEntity.ok(companyInfoService.delete(companyId));
	}

	@RequestMapping(value = "/{id}", method = RequestMethod.GET)
	public ResponseEntity<CompanyInfo> getCompanyInfoById(@PathVariable(value = "id", required = true) Long companyId) {
		return ResponseEntity.ok(companyInfoService.getById(companyId));
	}

}
