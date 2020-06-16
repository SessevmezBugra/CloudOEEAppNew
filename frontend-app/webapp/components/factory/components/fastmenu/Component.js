sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component"
], function(BaseComponent, Component) {
	"use strict";

	return BaseComponent.extend("workerapp.components.factory.components.fastmenu.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);

			var oParentComponent = Component.getOwnerComponentFor(Component.getOwnerComponentFor(this));
			// await this.getRouter().attachBeforeRouteMatched(async function (oEvent){
			// 	await this.UserService.initCheckSSO().then(function(isValid) {
			// 		if(!isValid){
			// 			oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
			// 		}
			// 	}.bind(this));
			// 	this.hideBusyIndicator();
			// }.bind(this), this);

			this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				if (!this.UserService.getKeycloak().authenticated || this.UserService.getKeycloak().isTokenExpired()) {
					oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
				}
				this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().initialize();
		}
	});
});
