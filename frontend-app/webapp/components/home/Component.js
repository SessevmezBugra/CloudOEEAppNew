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

			var oParentComponent = Component.getOwnerComponentFor(this);

			// this.getRouter().attachBeforeRouteMatched(function (oEvent) {
			// 	var target = this.getRouter().getHashChanger().hash;
			// 	if (this.keycloak.authenticated && !this.keycloak.isTokenExpired() && target != "factory" && target != "oeeapp") {
			// 		if(this.keycloak.hasRealmRole("COMPANY_OWNER") || this.keycloak.hasRealmRole("CLIENT_MANAGER") || this.keycloak.hasRealmRole("PLANT_MANAGER")) {
			// 			this.getRouter().getHashChanger().replaceHash("");
			// 		}else if (this.keycloak.hasRealmRole("OPERATOR")) {
			// 			this.getRouter().getHashChanger().replaceHash("");
			// 		}
					
			// 	}else if ((!this.keycloak.authenticated || this.keycloak.isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
			// 		window.location.pathname="/";
			// 	}
			// 	this.hideBusyIndicator();
			// }.bind(this));

			this.getRouter().initialize();
		}
	});
});
