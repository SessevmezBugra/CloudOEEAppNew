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
			// await this.getRouter().attachBeforeRouteMatched(async function (oEvent){
			// 	await this.UserService.initCheckSSO().then(function(isValid) {
			// 		if(!isValid){
			// 			// oParentComponent.getRouter().navTo("home");
			// 		}
			// 	}.bind(this));
			// 	this.hideBusyIndicator();
			// }.bind(this), this);

			this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				var target = this.getRouter().getHashChanger().hash;
				if (this.UserService.getKeycloak().authenticated && !this.UserService.getKeycloak().isTokenExpired() && target != "factory" && target != "oeeapp") {
					this.getRouter().getHashChanger().replaceHash("factory");
				}else if ((!this.UserService.getKeycloak().authenticated || this.UserService.getKeycloak().isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
					window.location.pathname="/index.html";
				}
				this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().initialize();
		}
	});
});
