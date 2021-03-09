sap.ui.define([
	"workerapp/base/BaseComponent",
	"workerapp/model/LocalStorageModel"
], function (BaseComponent, LocalStorageModel) {
	"use strict";

	return BaseComponent.extend("workerapp.Component", {

		metadata: {
			manifest: "json"
		},

		init: async function () {

			BaseComponent.prototype.init.apply(this, arguments);

			await this.UserService.initCheckSSO().then(function (isValid) {
				if (isValid && !this.keycloak.isTokenExpired()) {

					var localUserModel = new LocalStorageModel("localUserModel");
					localUserModel.setData(this.keycloak);
					this.setModel(localUserModel, "localUserModel");

					if(this.keycloak.hasRealmRole("COMPANY_OWNER") || this.keycloak.hasRealmRole("STAFF")){
						this.getRouter().getHashChanger().replaceHash("factory");
					} else {
						// var oComponentTargetInfo = {};
						// oComponentTargetInfo["home"] = {
						// 	route: "chooseRole",
						// 	parameters: {}
						// }
						// this.getRouter().navTo("home", {}, oComponentTargetInfo);
						// this.getRouter().navTo("chooseRole", {}, true);
					}

				} else {
					window.location.hash="";
				}
				this.hideBusyIndicator();
			}.bind(this));

			setInterval(function () {
				console.log("interval")
				this.keycloak.updateToken(240).then(function(refreshed) {
					if (refreshed) {
						var localUserModel = new LocalStorageModel("localUserModel");
						localUserModel.setData(this.keycloak);
						this.setModel(localUserModel, "localUserModel");
						console.log("Refresh token success");
					} else {
						console.log("Refresh token fail 1");
					}
				}.bind(this)).catch(function() {
					console.log("Refresh token fail 2");
				});
			}.bind(this), 180000);

			await this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				var target = this.getRouter().getHashChanger().hash;
				if (this.keycloak.authenticated && !this.keycloak.isTokenExpired() && target != "factory" && target != "oeeapp") {
					if(this.keycloak.hasRealmRole("COMPANY_OWNER") || this.keycloak.hasRealmRole("STAFF")){
						this.getRouter().getHashChanger().replaceHash("factory");
					}else if(target != "chooseRole"){
						// var oComponentTargetInfo = {};
						// oComponentTargetInfo["home"] = {
						// 	route: "chooseRole",
						// 	parameters: {}
						// }
						// this.getRouter().navTo("home", {}, oComponentTargetInfo);
						// this.UserService.logout();
						// this.getRouter().getHashChanger().replaceHash("");
						// window.location.pathname="/";
						// window.location.hash="";
						// window.location.pathname="/index.html";
					}
					
				} else if ((!this.keycloak.authenticated || this.keycloak.isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
					this.UserService.logout();
					// this.getRouter().getHashChanger().replaceHash("");
					// window.location.pathname="/index.html";
					// window.location.pathname="/";
					window.location.hash="";
				}
				// this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().initialize();
		},

	});
});
