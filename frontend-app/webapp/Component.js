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

			var keycloak = new Keycloak({
				url: 'http://localhost:8080/auth',
				realm: 'test-realm',
				clientId: 'AngularApp'
			});

			keycloak.init({
				onLoad: 'login-required'
			});

			await this.validateToken().then(function (isValid) {
				if (isValid) {
					// var target = this.getRouter().getHashChanger().hash;
					// if(target == "register" || target == ""){
					// 	this.getRouter().getHashChanger().replaceHash("factory");
					// }
				} else {
					this.getRouter().getHashChanger().replaceHash("");
				}
				this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().attachBeforeRouteMatched(async function (oEvent) {
				await this.validateToken().then(function (isValid) {
					var target = this.getRouter().getHashChanger().hash;
					if (isValid) {
						// if(target == "register" || target == ""){
						// 	this.getRouter().getHashChanger().replaceHash("factory");
						// }
					} else if (target != "register" && target != "") {
						this.getRouter().getHashChanger().replaceHash("");
					}
				}.bind(this));
				this.hideBusyIndicator();
			}.bind(this), this);

			this.getRouter().initialize();
		},

	});
});
