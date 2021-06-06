sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component",
	'sap/ui/model/json/JSONModel'
], function(BaseComponent, Component, JSONModel) {
	"use strict";

	return BaseComponent.extend("workerapp.components.factory.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);
			var oModel = new JSONModel({
				sideNavigationExpanded: true
			});
			this.setModel(oModel, "factoryGlobalModel");
			var oParentComponent = Component.getOwnerComponentFor(this);

			// this.getRouter().attachBeforeRouteMatched(function (oEvent) {
			// 	if (!this.keycloak.authenticated || this.keycloak.isTokenExpired() || !(this.keycloak.hasRealmRole("ORGANIZER") || this.keycloak.hasRealmRole("CLIENT_MANAGER") || this.keycloak.hasRealmRole("PLANT_MANAGER"))) {
			// 		oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
			// 		this.getRouter().getHashChanger().replaceHash("");
			// 	}
			// 	this.hideBusyIndicator();
			// }.bind(this));

			this.getRouter().initialize();
		}
	});
});
