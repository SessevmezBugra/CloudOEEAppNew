sap.ui.define([
	"workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel",
	"workerapp/model/popup",
	"workerapp/model/auth",
	"workerapp/services/authservice"
], function (BaseController, JSONModel, Popup, AuthUtil, AuthService) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.ChooseRole", {

		onInit: function () {
			
		},

		chooseRole: function (oEvent) {
			var oButton = oEvent.getSource();
			this.group = oButton.data("extraValue");
			Popup.messageBox("confirm", "Devam etmek istiyor musunuz?", function(isOk){
				if(isOk) {
					this.updateUserRole(this.group);
				}
			}.bind(this));
		},

		updateUserRole: function(group) {
			this.showBusyIndicator();
			var user = {
				username: AuthUtil.getUsername(),
				groupName: group
			}
			AuthService.updateUserGroup(user).then(function(response) {
				this.hideBusyIndicator();
			}.bind(this)).catch(function(response) {
				this.hideBusyIndicator();
			}.bind(this));
		}

	});
});