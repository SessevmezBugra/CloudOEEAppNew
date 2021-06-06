sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component"
], function(BaseComponent, Component) {
	"use strict";

	return BaseComponent.extend("workerapp.components.home.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);

			var target = this.getRouter().getHashChanger().hash;
				if (this.keycloak.authenticated && !this.keycloak.isTokenExpired() && target != "factory" && target != "oeeapp") {
					if(this.keycloak.hasRealmRole("ORGANIZER") || this.keycloak.hasRealmRole("STAFF")){
						this.getParentComponent(this).getRouter().navTo("factory", {}, true /*no history*/);
					}else if(target != "chooseRole"){
						this.getRouter().getHashChanger().replaceHash("chooseRole");
					}
					
				} else if ((!this.keycloak.authenticated || this.keycloak.isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
					this.UserService.logout();
					window.location.hash="";
				}

			await this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				var target = this.getRouter().getHashChanger().hash;
				if (this.keycloak.authenticated && !this.keycloak.isTokenExpired() && target != "factory" && target != "oeeapp") {
					if(this.keycloak.hasRealmRole("ORGANIZER") || this.keycloak.hasRealmRole("STAFF")){

					}else if(target != "chooseRole"){
						this.getRouter().getHashChanger().replaceHash("chooseRole");
					}
					
				} else if ((!this.keycloak.authenticated || this.keycloak.isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
					this.UserService.logout();
					window.location.hash="";
				}
			}.bind(this));

			this.getRouter().initialize();
		}
	});
});
