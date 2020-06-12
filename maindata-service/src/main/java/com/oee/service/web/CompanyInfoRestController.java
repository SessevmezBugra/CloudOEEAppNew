package com.oee.service.web;

import java.security.Principal;
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

@RestController
@RequestMapping(ApiPaths.CompanyInfoCtrl.CTRL)
public class CompanyInfoRestController {

	@Autowired
	private Environment environment;

	private Logger logger = LoggerFactory.getLogger(this.getClass());

	private final CompanyInfoService companyInfoService;
	private final StockServiceProxy stockServiceProxy;

	public CompanyInfoRestController(CompanyInfoService companyInfoService, StockServiceProxy stockServiceProxy) {
		this.companyInfoService = companyInfoService;
		this.stockServiceProxy = stockServiceProxy;
	}

//	@RolesAllowed("user")
	@RequestMapping(method = RequestMethod.POST)
	public ResponseEntity<CompanyInfo> createCompanyInfo(@RequestBody CompanyInfo companyInfo, Principal principal) {
//	      user.setId(token.getId());
//	      user.setUserName(token.getName());
//	      Map<String, Object> otherClaims = token.getOtherClaims();
//	      user.setCustomAttributes(otherClaims);
		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
		KeycloakPrincipal<?> userPrincipal=(KeycloakPrincipal<?>)token.getPrincipal();
        KeycloakSecurityContext session = userPrincipal.getKeycloakSecurityContext();
        AccessToken accessToken = session.getToken();
        String username = accessToken.getPreferredUsername();
        String emailID = accessToken.getEmail();
        String lastname = accessToken.getFamilyName();
        String firstname = accessToken.getGivenName();
        String realmName = accessToken.getIssuer();            
        Access realmAccess = accessToken.getRealmAccess();
        Set<String> roles = realmAccess.getRoles();
        System.err.println(accessToken.toString());
//        System.err.println(accessToken.get);
//		System.err.println(principal.toString());
//		KeycloakAuthenticationToken token = (KeycloakAuthenticationToken) principal;
//		System.err.println(token.toString());
//		System.err.println(token.getCredentials());
//		System.err.println(SecurityContextHolder.getContext().getAuthentication().getAuthorities().toString());
//		KeycloakSecurityContext session = principal.getKeycloakSecurityContext();
//        AccessToken accessToken = session.getToken();
//        username = accessToken.getPreferredUsername();
//        emailID = accessToken.getEmail();
//        lastname = accessToken.getFamilyName();
//        firstname = accessToken.getGivenName();
//        realmName = accessToken.getIssuer();            
//        Access realmAccess = accessToken.getRealmAccess();
//        roles = realmAccess.getRoles();
//		logger.info("{}", environment.getProperty("local.server.port"));
		StockInfoDto stockInfoDto = new StockInfoDto();
		stockInfoDto.setQuantity(200.0);
		stockServiceProxy.createStockInfo(stockInfoDto);
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
