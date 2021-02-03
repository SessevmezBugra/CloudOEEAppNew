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
import lombok.RequiredArgsConstructor;
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

import javax.persistence.EntityExistsException;
import javax.ws.rs.core.Response;

@Service
@RequiredArgsConstructor
public class KeycloakAdminClientService {

    private final CurrentUserProvider currentUserProvider;
    private final Environment environment;
    private final ResponsibleAreaService responsibleAreaService;
    private final MainDataServiceClient mainDataServiceClient;
    private final UserEntityRepository userEntityRepository;

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

    public Boolean addUserToCompanyOwnerGroup(String userId){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.COMPANY_OWNER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean addUserToClientManagerGroup(String userId){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.CLIENT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean addUserToPlantManagerGroup(String userId){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.PLANT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean addUserToOperatorGroup(String userId){
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.OPERATOR.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.joinGroup(groupId.get(0));
        return Boolean.TRUE;
    }

    public Boolean createUser(CurrentUser userDto){
        System.err.println("TEST");
        if(StringUtils.isEmpty(userDto.getUsername()) || StringUtils.isEmpty(userDto.getPasswordRetry()) || StringUtils.isEmpty(userDto.getPassword()) || StringUtils.isEmpty(userDto.getAreaId())) {
            throw new RuntimeException("Bilgilerin eksiksiz oldugundan emin olun.");
        }

        if(!userDto.getPassword().equals(userDto.getPasswordRetry())) {
            throw new RuntimeException("Sifrelerin ayni oldugundan emin olun.");
        }

        if(userDto.getRoles() == null || userDto.getRoles().size() <= 0) {
            throw new RuntimeException("Bir rol secmelisiniz.");
        }
        System.err.println("TEST30");
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        System.err.println("TEST31");
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        System.err.println("TEST32");
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        System.err.println("TEST33");
        GroupsResource groupsResource = realmResource.groups();
        System.err.println("TEST34");
        System.err.println(userDto.getRoles().get(0));
        System.err.println(groupsResource.groups().size());
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(userDto.getRoles().get(0))).map(GroupRepresentation::getId).collect(Collectors.toList());

        System.err.println("TEST1");
        if(userDto.getRoles() == null || userDto.getRoles().size() <= 0 || groupId == null || groupId.size() <= 0) {
            throw new RuntimeException("Lutfen dogru bir rol secin");
        }
        UsersResource usersRessource = realmResource.users();

        UserRepresentation userRepresentation = new UserRepresentation();
        userRepresentation.setUsername(userDto.getUsername());
        userRepresentation.setFirstName(userDto.getFirstName());
        userRepresentation.setLastName(userDto.getLastName());
        userRepresentation.setEnabled(true);
        userRepresentation.setEmail(userDto.getEmail());
        System.err.println(userDto.getEmail());
        userRepresentation.setEmailVerified(true);
        Response response = usersRessource.create(userRepresentation);
        System.err.println("TEST2");
        String userId = CreatedResponseUtil.getCreatedId(response);
        CredentialRepresentation passwordCred = new CredentialRepresentation();
        passwordCred.setTemporary(false);
        passwordCred.setType(CredentialRepresentation.PASSWORD);
        passwordCred.setValue(userDto.getPassword());
        UserResource userResource = usersRessource.get(userId);
        // Set password credential
        userResource.resetPassword(passwordCred);
        System.err.println("TEST3");
        List<String> groups = new ArrayList<>();

        ResponsibleArea responsibleArea = new ResponsibleArea();
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        responsibleArea.setUserEntity(userEntity);
        responsibleArea.setAreaId(userDto.getAreaId());

        if (userDto.getRoles().contains(UserRole.OPERATOR.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.OPERATOR.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.PLANT);
            responsibleArea.setUserRole(UserRole.OPERATOR);
        } else if (userDto.getRoles().contains(UserRole.PLANT_MANAGER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.PLANT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.PLANT);
            responsibleArea.setUserRole(UserRole.PLANT_MANAGER);
        } else if (userDto.getRoles().contains(UserRole.CLIENT_MANAGER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.CLIENT_MANAGER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.CLIENT);
            responsibleArea.setUserRole(UserRole.CLIENT_MANAGER);
        } else if (userDto.getRoles().contains(UserRole.COMPANY_OWNER.name())) {
            groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.COMPANY_OWNER.name())).map(GroupRepresentation::getId).collect(Collectors.toList());
            responsibleArea.setAreaType(AreaType.COMPANY);
            responsibleArea.setUserRole(UserRole.COMPANY_OWNER);
        }
        userResource.leaveGroup(groupsResource.groups().stream().filter(gr -> gr.getName().equals(UserRole.COMPANY_OWNER.name())).map(GroupRepresentation::getId).collect(Collectors.toList()).get(0));
        userResource.joinGroup(groupId.get(0));
        responsibleAreaService.create(responsibleArea);
        return Boolean.TRUE;
    }

    public Boolean updateUser(CurrentUser currentUserDto) {
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersRessource = realmResource.users();
        UserResource userResource = usersRessource.get(currentUserDto.getUserId());
        UserRepresentation userRepresentation = userResource.toRepresentation();
        userRepresentation.setFirstName(currentUserDto.getFirstName());
        userRepresentation.setLastName(currentUserDto.getLastName());
        userRepresentation.setUsername(currentUserDto.getUsername());
        userRepresentation.setEmail(currentUserDto.getEmail());
        userResource.update(userRepresentation);
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
            allUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(companyResponsibleAreaIds, AreaType.COMPANY.name(), clientResponsibleAreaIds, AreaType.CLIENT.name(), plantResponsibleAreaIds, AreaType.PLANT.name());
        } else if (currentUser.getRoles().contains(UserRole.CLIENT_MANAGER.getRole())) {
            List<ClientDto> clientDtos = mainDataServiceClient.getClientsByLoggedUser().getBody();
            for (ClientDto clientDto : clientDtos) {
                clientResponsibleAreaIds.add(clientDto.getClientId());
                for (PlantDto plant : clientDto.getPlants()) {
                    plantResponsibleAreaIds.add(plant.getPlantId());
                }
            }
            allUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(null, null, clientResponsibleAreaIds, AreaType.CLIENT.name(), plantResponsibleAreaIds, AreaType.PLANT.name());
        } else if (currentUser.getRoles().contains(UserRole.PLANT_MANAGER.getRole())) {
            List<PlantDto> plantDtos = mainDataServiceClient.getPlantsByLoggedUser().getBody();
            for (PlantDto plantDto : plantDtos) {
                plantResponsibleAreaIds.add(plantDto.getPlantId());
            }
            allUsers = userEntityRepository.findByResponsibleAreasAreaIdIn(null, null, null, null, plantResponsibleAreaIds, AreaType.PLANT.name());
        }

        List<UserEntityOnly>  allUsersWithoutCurrentUser = allUsers.stream().filter(userEntityOnly -> !currentUser.getUserId().equals(userEntityOnly.getId())).collect(Collectors.toList());

        return allUsersWithoutCurrentUser;
    }

    public UserRepresentation findUserById(String userId) {
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        UserRepresentation userRepresentation = userResource.toRepresentation();
        return userRepresentation;
    }

    public Boolean deleteById(String userId) {
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        usersResource.delete(userId);
        responsibleAreaService.deleteByUserId(userId);
        return Boolean.TRUE;
    }


    public Boolean isRoleExist(ResponsibleArea responsibleArea) {
        ResponsibleArea foundedArea = responsibleAreaService.findByUserIdAndUserRoleAndAreaId(responsibleArea.getUserEntity().getId(), responsibleArea.getUserRole(), responsibleArea.getAreaId());

        if(foundedArea != null) {
            return true;
        }
        return false;
    }

    public Boolean addCompanyOwnerRole(ResponsibleArea responsibleArea) {
        responsibleArea.setUserRole(UserRole.COMPANY_OWNER);
        responsibleArea.setAreaType(AreaType.COMPANY);
        if (isRoleExist(responsibleArea)){
            throw new EntityExistsException("Bu rol bu kullanici icin mevcuttur.");
        }
        responsibleAreaService.create(responsibleArea);
        addUserToCompanyOwnerGroup(responsibleArea.getUserEntity().getId());
        return Boolean.TRUE;
    }

    public Boolean addClientManagerRole(ResponsibleArea responsibleArea) {
        responsibleArea.setUserRole(UserRole.CLIENT_MANAGER);
        responsibleArea.setAreaType(AreaType.CLIENT);
        if (isRoleExist(responsibleArea)){
            throw new EntityExistsException("Bu rol bu kullanici icin mevcuttur.");
        }
        responsibleAreaService.create(responsibleArea);
        addUserToClientManagerGroup(responsibleArea.getUserEntity().getId());
        return Boolean.TRUE;
    }

    public Boolean addPlantManagerRole(ResponsibleArea responsibleArea) {
        responsibleArea.setUserRole(UserRole.PLANT_MANAGER);
        responsibleArea.setAreaType(AreaType.PLANT);
        if (isRoleExist(responsibleArea)){
            throw new EntityExistsException("Bu rol bu kullanici icin mevcuttur.");
        }
        responsibleAreaService.create(responsibleArea);
        addUserToPlantManagerGroup(responsibleArea.getUserEntity().getId());
        return Boolean.TRUE;
    }

    public Boolean addOperatorRole(ResponsibleArea responsibleArea) {
        responsibleArea.setUserRole(UserRole.OPERATOR);
        responsibleArea.setAreaType(AreaType.PLANT);
        if (isRoleExist(responsibleArea)){
            throw new EntityExistsException("Bu rol bu kullanici icin mevcuttur.");
        }
        responsibleAreaService.create(responsibleArea);
        addUserToOperatorGroup(responsibleArea.getUserEntity().getId());
        return Boolean.TRUE;
    }

    public Boolean deleteByResponsibleAreaId(Long responsibleAreaId) {
        ResponsibleArea responsibleArea = responsibleAreaService.findById(responsibleAreaId);
        List<ResponsibleArea> responsibleAreas = responsibleArea.getUserEntity().getResponsibleAreas();
        Boolean isSameRoleExist = false;
        for (ResponsibleArea area : responsibleAreas) {
            System.err.println(area.getUserRole().getRole());
            if(responsibleArea.getId() != area.getId() && responsibleArea.getUserRole().equals(area.getUserRole())) {
                isSameRoleExist = true;
            }
        }
        System.err.println(responsibleAreaId);
//        responsibleAreas.remove(responsibleArea);
        responsibleAreaService.delete(responsibleAreaId);
        if(!isSameRoleExist) {
            removeFromGroup(responsibleArea.getUserEntity().getId(), responsibleArea.getUserRole().name());
        }
        return Boolean.TRUE;
    }

    public Boolean removeFromGroup(String userId, String groupName) {
        KeycloakAdminClientConfig keycloakAdminClientConfig = KeycloakAdminClientUtils.loadConfig(environment);
        Keycloak keycloak = KeycloakAdminClientUtils.getKeycloakClient(keycloakAdminClientConfig);
        RealmResource realmResource = keycloak.realm(keycloakAdminClientConfig.getRealm());
        UsersResource usersResource = realmResource.users();
        UserResource userResource = usersResource.get(userId);
        GroupsResource groupsResource = realmResource.groups();
        List<String> groupId = groupsResource.groups().stream().filter(gr -> gr.getName().equals(groupName)).map(GroupRepresentation::getId).collect(Collectors.toList());
        userResource.leaveGroup(groupId.get(0));
        return Boolean.TRUE;
    }
}