sap.ui.define([
	"workerapp/base/BaseComponent"
], function (BaseComponent) {
	"use strict";

	return BaseComponent.extend("workerapp.Component", {

		metadata: {
			manifest: "json"
		},

		init: async function () {
			BaseComponent.prototype.init.apply(this, arguments);

			await this.UserService.initCheckSSO().then(function (isValid) {
				if (isValid && !this.UserService.getKeycloak().isTokenExpired()) {
					// var target = this.getRouter().getHashChanger().hash;
					// if(target == "register" || target == ""){
					this.getRouter().getHashChanger().replaceHash("factory");
					// }
				} else {
					this.getRouter().getHashChanger().replaceHash("");
				}
				this.hideBusyIndicator();
			}.bind(this));

			await this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				var target = this.getRouter().getHashChanger().hash;
				if (this.UserService.getKeycloak().authenticated && !this.UserService.getKeycloak().isTokenExpired() && target != "factory") {
					this.getRouter().getHashChanger().replaceHash("factory");
				} else if ((!this.UserService.getKeycloak().authenticated || this.UserService.getKeycloak().isTokenExpired()) && target == "factory") {
					this.getRouter().getHashChanger().replaceHash("");
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
