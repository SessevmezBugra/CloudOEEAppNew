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
				if (isValid && !this.UserService.getKeycloak().isTokenExpired()) {

					var localUserModel = new LocalStorageModel("localUserModel");
					localUserModel.setData(this.UserService.getKeycloak());
					this.setModel(localUserModel, "localUserModel");

					this.getRouter().getHashChanger().replaceHash("factory");

				} else {
					this.getRouter().getHashChanger().replaceHash("");
				}
				this.hideBusyIndicator();
			}.bind(this));

			setInterval(function () {
				console.log("interval")
				this.UserService.getKeycloak().updateToken(240).then(function(refreshed) {
					if (refreshed) {
						var localUserModel = new LocalStorageModel("localUserModel");
						localUserModel.setData(this.UserService.getKeycloak());
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
				if (this.UserService.getKeycloak().authenticated && !this.UserService.getKeycloak().isTokenExpired() && target != "factory" && target != "oeeapp") {
					this.getRouter().getHashChanger().replaceHash("factory");
				} else if ((!this.UserService.getKeycloak().authenticated || this.UserService.getKeycloak().isTokenExpired()) && (target == "factory" || target == "oeeapp")) {
					// this.getRouter().getHashChanger().replaceHash("");
					window.location.pathname="/index.html";
				}
				// this.hideBusyIndicator();
			}.bind(this));
			

			// this.getRouter().attachBeforeRouteMatched(async function (oEvent) {
			// 	await this.UserService.initCheckSSO().then(function (isValid) {
			// 		var target = this.getRouter().getHashChanger().hash;
			// 		if (isValid) {
			// 		// 	if(target == "register" || target == ""){
			// 				this.getRouter().getHashChanger().replaceHash("factory");
			// 			// }
			// 		} else {
			// 			this.getRouter().getHashChanger().replaceHash("");
			// 		}
			// 	}.bind(this));
			// 	this.hideBusyIndicator();
			// }.bind(this), this);


			// await this.validateToken().then(function (isValid) {
			// 	if (isValid) {
			// 		var target = this.getRouter().getHashChanger().hash;
			// 		if(target == "register" || target == ""){
			// 			this.getRouter().getHashChanger().replaceHash("factory");
			// 		}
			// 	} else {
			// 		this.getRouter().getHashChanger().replaceHash("");
			// 	}
			// 	this.hideBusyIndicator();
			// }.bind(this));


			// this.getRouter().attachBeforeRouteMatched(async function (oEvent) {
			// 	await this.validateToken().then(function (isValid) {
			// 		var target = this.getRouter().getHashChanger().hash;
			// 		if (isValid) {
			// 			if(target == "register" || target == ""){
			// 				this.getRouter().getHashChanger().replaceHash("factory");
			// 			}
			// 		} else if (target != "register" && target != "") {
			// 			this.getRouter().getHashChanger().replaceHash("");
			// 		}
			// 	}.bind(this));
			// 	this.hideBusyIndicator();
			// }.bind(this), this);


			this.getRouter().initialize();
		},

	});
});
