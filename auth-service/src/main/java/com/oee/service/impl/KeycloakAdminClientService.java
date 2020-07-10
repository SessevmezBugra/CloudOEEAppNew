package com.oee.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.oee.client.MainDataServiceClient;
import com.oee.config.CurrentUserProvider;
import com.oee.dto.*;
import com.oee.entity.ResponsibleArea;
import com.oee.entity.UserEntity;
import com.oee.enums.AreaType;
import com.oee.enums.UserRole;
import com.oee.repository.UserEntityRepository;
import com.oee.service.ResponsibleAreaService;
import feign.Client;
import org.keycloak.KeycloakPrincipal;
import org.keycloak.adapters.RefreshableKeycloakSecurityContext;
import org.keycloak.admin.client.CreatedResponseUtil;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.resource.*;
import org.keycloak.representations.idm.CredentialRepresentation;
import org.keycloak.representations.idm.GroupRepresentation;
import org.keycloak.representations.idm.UserRepresentation;
import org.springframework.core.env.Environment;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import com.oee.config.KeycloakAdminClientConfig;
import com.oee.util.KeycloakAdminClientUtils;
import org.springframework.util.StringUtils;

import javax.ws.rs.core.Response;

@Service
public class KeycloakAdminClientService {

    private final CurrentUserProvider currentUserProvider;
    private final Environment environment;
    private final ResponsibleAreaService responsibleAreaService;
    private final MainDataServiceClient mainDataServiceClient;
    private final UserEntityRepository userEntityRepository;

    public KeycloakAdminClientService(CurrentUserProvider currentUserProvider, Environment environment, ResponsibleAreaService responsibleAreaService, MainDataServiceClient mainDataServiceClient, UserEntityRepository userEntityRepository) {
        this.currentUserProvider = currentUserProvider;
        this.environment = environment;
        this.responsibleAreaService = responsibleAreaService;
        this.mainDataServiceClient = mainDataServiceClient;
        this.userEntityRepository = userEntityRepository;
    }

    public List<String> getCurrentUserRoles() {
        return currentUserProvider.getCurrentUser().getRoles();
    }

    public Object getUserProfileOfLoggedUser() {

        @SuppressWarnings("unchecked")
        KeycloakPrincipal<RefreshableKeycloakSecurityContext> principal = (KeycloakPrincipal<RefreshableKeycloakSecurityContext>) SecurityContextHolder.getContext()
                .getAuthentication().getPrincipal();
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(principal.getKeycloakSecurityContext(), keycloakAdminClientConfig);

        // Get realm
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(currentUserProvider.getCurrentUser().getUserId());
        UserRepresentation userRepresentation = userResource.toRepresentation();

        return userRepresentation;
    }

    public Boolean addUserToCompanyOwnerGroup(){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(currentUserProvider.getCurrentUser().getUserId());
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals("COMPANY_OWNER")).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean createUser(CurrentUser userDto){
        if(StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPasswordRetry()) || StringUtils.isEmpty(userDto.getPassword()) || StringUtils.isEmpty(userDto.getAreaId())) {
            throw new RuntimeException("Bilgilerin eksiksiz oldugundan emin olun.");
        }

        if(!userDto.getPassword().equals(userDto.getPasswordRetry())) {
            throw new RuntimeException("Sifrelerin ayni oldugundan emin olun.");
        }

        if(userDto.getRoles() == null || userDto.getRoles().size() <= 0) {
            throw new RuntimeException("Bir rol secmelisiniz.");
        }

        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(userDto.getRoles().get(0))).map(GroupRepresentation::getId).collect(Collectors.toList());

        if(userDto.getRoles() == null || userDto.getRoles().size() <= 0 || groupId == null || groupId.size() <= 0) {
            throw new RuntimeException("Lutfen dogru bir rol secin");
        }
        UsersResource usersRessource = realmResource.users();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        Response response = usersRessource.create(userRepresentation);

        String userId = CreatedResponseUtil.getCreatedId(response);
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        UserResource userResource = usersRessource.get(userId);
        // Set password credential
        userResource.resetPassword(passwordCred);

        List<String> groups = new ArrayList<>();

        ResponsibleArea responsibleArea = new ResponsibleArea();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        responsibleArea.setUserEntity(userEntity);
        responsibleArea.setAreaId(userDto.getAreaId());

        if (userDto.getRoles().contains(UserRole.OPERATOR.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.OPERATOR.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.PLANT);
        } else if (userDto.getRoles().contains(UserRole.PLANT_MANAGER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.PLANT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.PLANT);
        } else if (userDto.getRoles().contains(UserRole.CLIENT_MANAGER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.CLIENT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.CLIENT);
        } else if (userDto.getRoles().contains(UserRole.COMPANY_OWNER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.COMPANY_OWNER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.COMPANY);
        }
        userResource.joinGroup(groupId.get(0));
        responsibleAreaService.create(responsibleArea);
        return Boolean.TRUE;
    }

    public List<UserEntityOnly> findAllUsersByLoggedUser() {
        CurrentUser currentUser = currentUserProvider.getCurrentUser();
        List<Long> companyResponsibleAreaIds = new ArrayList<>();
        List<Long> clientResponsibleAreaIds = new ArrayList<>();
        List<Long> plantResponsibleAreaIds = new ArrayList<>();
        List<UserEntityOnly> companyUsers = new ArrayList<>();
        List<UserEntityOnly> clientUsers = new ArrayList<>();
        List<UserEntityOnly> plantUsers = new ArrayList<>();
        List<UserEntityOnly> allUsers = new ArrayList<>();
        if(currentUser.getRoles().contains(UserRole.COMPANY_OWNER.getRole())) {
            List<CompanyDto> companyDtos = mainDataServiceClient.getCompaniesByLoggedUser().getBody();
            for (CompanyDto companyDto : companyDtos) {
                companyResponsibleAreaIds.add(companyDto.getCompanyId());
                for (ClientDto client : companyDto.getClients()) {
                    clientResponsibleAreaIds.add(client.getClientId());
                    for (PlantDto plant : client.getPlants()) {
                        plantResponsibleAreaIds.add(plant.getPlantId());
                    }
                }
            }
            companyUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(companyResponsibleAreaIds, AreaType.COMPANY.name());
            clientUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(clientResponsibleAreaIds, AreaType.CLIENT.name());
            plantUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(plantResponsibleAreaIds, AreaType.PLANT.name());
            allUsers.addAll(companyUsers);
            allUsers.addAll(clientUsers);
            allUsers.addAll(plantUsers);
        } else if (currentUser.getRoles().contains(UserRole.CLIENT_MANAGER.getRole())) {
            List<ClientDto> clientDtos = mainDataServiceClient.getClientsByLoggedUser().getBody();
            for (ClientDto clientDto : clientDtos) {
                clientResponsibleAreaIds.add(clientDto.getClientId());
                for (PlantDto plant : clientDto.getPlants()) {
                    plantResponsibleAreaIds.add(plant.getPlantId());
                }
            }
            clientUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(clientResponsibleAreaIds, AreaType.CLIENT.name());
            plantUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(plantResponsibleAreaIds, AreaType.PLANT.name());
            allUsers.addAll(clientUsers);
            allUsers.addAll(plantUsers);
        } else if (currentUser.getRoles().contains(UserRole.PLANT_MANAGER.getRole())) {
            List<PlantDto> plantDtos = mainDataServiceClient.getPlantsByLoggedUser().getBody();
            for (PlantDto plantDto : plantDtos) {
                plantResponsibleAreaIds.add(plantDto.getPlantId());
            }
            plantUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(plantResponsibleAreaIds, AreaType.PLANT.name());
            allUsers.addAll(plantUsers);
        }
        return allUsers;
    }
}