package com.oee.config;

import lombok.RequiredArgsConstructor;
import org.jboss.resteasy.client.jaxrs.ResteasyClientBuilder;
import org.keycloak.OAuth2Constants;
import org.keycloak.admin.client.Keycloak;
import org.keycloak.admin.client.KeycloakBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * @author Edward P. Legaspi | czetsuya@gmail.com
 * 
 * @version 0.0.1
 * @since 0.0.1
 */

@Configuration
@RequiredArgsConstructor
public class KeycloakAdminClient {

	private final Environment environment;

	private static Logger log = LoggerFactory.getLogger(KeycloakAdminClient.class);


	/**
	 * Loads the keycloak configuration from system property.
	 * 
	 * @return keycloak configuration
	 * @see KeycloakAdminClientConfig
	 */

	@Bean
	public KeycloakAdminClientConfig loadConfig() {
		KeycloakAdminClientConfig.KeycloakAdminClientConfigBuilder builder = KeycloakAdminClientConfig.builder();
		try {
			builder.serverUrl(environment.getProperty("keycloak.auth-server-url"))
					.realm(environment.getProperty("keycloak.realm"))
					.clientId(environment.getProperty("keycloak.resource"))
					.clientSecret(environment.getProperty("keycloak.credentials.secret"))
					.admin(environment.getProperty("keycloak-admin-user"))
					.password(environment.getProperty("keycloak-admin-password"));
		} catch (Exception e) {
			log.error("Error: Loading keycloak admin configuration => {}", e.getMessage());
		}

		KeycloakAdminClientConfig config = builder.build();
		log.debug("Found keycloak configuration: {}", config);

		return config;
	}

	/**
	 * It builds a {@link Keycloak} client from a given configuration. This client
	 * is used to communicate with the Keycloak instance via REST API.
	 * 
	 * @param session the security context
	 * @param config  keycloak configuration
	 * @return Keycloak instance
	 * @see Keycloak
	 * @see KeycloakAdminClientConfig
	 */
//	public Keycloak getKeycloakClient(KeycloakSecurityContext session, KeycloakAdminClientConfig config) {
//
//		return KeycloakBuilder.builder() //
//				.serverUrl(config.getServerUrl()) //
//				.realm(config.getRealm()) //
//			    .grantType(OAuth2Constants.CLIENT_CREDENTIALS)
//				.clientId(config.getClientId()) //
//				.clientSecret(config.getClientSecret())
////				.username(config.getAdmin())
////				.password(config.getPassword())
//				.resteasyClient(
//				        new ResteasyClientBuilder()
//				            .connectionPoolSize(10).build()
//				    )
//				.build();
//	}

	@Bean
	public Keycloak getKeycloakClient(KeycloakAdminClientConfig config) {

		return KeycloakBuilder.builder() //
				.serverUrl(config.getServerUrl()) //
				.realm(config.getRealm()) //
				.grantType(OAuth2Constants.CLIENT_CREDENTIALS)
				.clientId(config.getClientId()) //
				.clientSecret(config.getClientSecret())
				.resteasyClient(
						new ResteasyClientBuilder()
								.connectionPoolSize(10).build()
				)
				.build();
	}

//	public static Keycloak getKeycloakClientPasswordCredential(KeycloakAdminClientConfig config) {
//
//		return KeycloakBuilder.builder() //
//				.serverUrl(config.getServerUrl()) //
//				.realm(config.getRealm()) //
//				.grantType(OAuth2Constants.PASSWORD)
//				.clientId(config.getClientId()) //
//				.clientSecret(config.getClientSecret())
//				.username(config.getAdmin())
//				.password(config.getPassword())
//				.resteasyClient(
//						new ResteasyClientBuilder()
//								.connectionPoolSize(10).build()
//				)
//				.build();
//	}

//	public static void addUserToGroup(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig, String groupName, String userId) {
//		keycloak.realm(keycloakAdminClientConfig.getRealm()).users().get(userId).joinGroup(groupName);
//	}
	/**
	 * Adds a role to a composite role. A composite role is just a role that
	 * contains sub roles.
	 * 
	 * @param keycloak                  keycloak instance
	 * @param keycloakAdminClientConfig keycloak configuration
	 * @param client                    client id
	 * @param role                      role to be added
	 * @param compositeRole             where the role will be added
	 */
//	public static void addRoleToListOf(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig,
//			String client, String role, String compositeRole) {
//
//		final String clientUuid = keycloak.realm(keycloakAdminClientConfig.getRealm()).clients().findByClientId(client)
//				.get(0).getId();
//
//		RolesResource rolesResource = keycloak.realm(keycloakAdminClientConfig.getRealm()).clients().get(clientUuid)
//				.roles();
//
//		final List<RoleRepresentation> existingRoles = rolesResource.list();
//
//		final boolean roleExists = existingRoles.stream().anyMatch(r -> r.getName().equals(role));
//
//		if (!roleExists) {
//			RoleRepresentation roleRepresentation = new RoleRepresentation();
//			roleRepresentation.setName(role);
//			roleRepresentation.setClientRole(true);
//			roleRepresentation.setComposite(false);
//
//			rolesResource.create(roleRepresentation);
//		}
//
//		final boolean compositeExists = existingRoles.stream().anyMatch(r -> r.getName().equals(compositeRole));
//
//		if (!compositeExists) {
//			RoleRepresentation compositeRoleRepresentation = new RoleRepresentation();
//			compositeRoleRepresentation.setName(compositeRole);
//			compositeRoleRepresentation.setClientRole(true);
//			compositeRoleRepresentation.setComposite(true);
//
//			rolesResource.create(compositeRoleRepresentation);
//		}
//
//		final RoleResource compositeRoleResource = rolesResource.get(compositeRole);
//
//		final boolean alreadyAdded = compositeRoleResource.getRoleComposites().stream()
//				.anyMatch(r -> r.getName().equals(role));
//
//		if (!alreadyAdded) {
//			final RoleRepresentation roleToAdd = rolesResource.get(role).toRepresentation();
//			compositeRoleResource.addComposites(Collections.singletonList(roleToAdd));
//		}
//	}

	/**
	 * Removes a given role from a composite role.
	 * 
	 * @param keycloak                  keycloak instance
	 * @param keycloakAdminClientConfig keycloak configuration
	 * @param role                      role to be deleted
	 * @param compositeRole             where the role should be deleted
	 */
//	public static void removeRoleInListOf(Keycloak keycloak, KeycloakAdminClientConfig keycloakAdminClientConfig,
//			String role, String compositeRole) {
//
//		final String clientUuid = keycloak.realm(keycloakAdminClientConfig.getRealm()).clients()
//				.findByClientId(keycloakAdminClientConfig.getClientId()).get(0).getId();
//
//		final RolesResource rolesResource = keycloak.realm(keycloakAdminClientConfig.getRealm()).
//				clients()
//				.get(clientUuid).roles();
//
//		final RoleResource compositeRoleResource = rolesResource.get(compositeRole);
//
//		try {
//			final RoleRepresentation roleToDelete = rolesResource.get(role).toRepresentation();
//			compositeRoleResource.getRoleComposites().remove(roleToDelete);
//
//		} catch (NotFoundException e) {
//			log.warn("Role {} does not exists!", role);
//		}
//	}

	/**
	 * Removes a role from a given list of roles.
	 * 
	 * @param listOfRoleRepresentation list of roles
	 * @param roleToBeRemove           role to be remove from the list
	 * @return
	 */
//	public static List<RoleRepresentation> removeRoleInList(List<RoleRepresentation> listOfRoleRepresentation,
//			RoleRepresentation roleToBeRemove) {
//
//		listOfRoleRepresentation.remove(roleToBeRemove);
//
//		List<RoleRepresentation> updatedListRoleRepresentation = new ArrayList<>();
//		for (RoleRepresentation roleRepresentationItem : listOfRoleRepresentation) {
//			if (!roleToBeRemove.getName().equalsIgnoreCase(roleRepresentationItem.getName())) {
//				updatedListRoleRepresentation.add(roleRepresentationItem);
//			}
//		}
//
//		return updatedListRoleRepresentation;
//	}


}