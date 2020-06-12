sap.ui.define([
	"workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel"
], function (BaseController, JSONModel) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.App", {

		onInit: function () {
			this.getView().addStyleClass(this.getOwnerComponent().getContentDensityClass());
		},

		onSelectTab: function() {
			this.getRouter().navTo("home");
		},

		goToLoginPage: function() {
			this.UserService.login();
		}

	});
});