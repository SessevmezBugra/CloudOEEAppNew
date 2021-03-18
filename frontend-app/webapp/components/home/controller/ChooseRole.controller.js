sap.ui.define([
	"workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel",
	"workerapp/model/popup",
	"workerapp/model/auth",
	"workerapp/services/authservice",
	"workerapp/model/constant",
], function (BaseController, JSONModel, Popup, AuthUtil, AuthService, Const) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.ChooseRole", {

		onInit: function () {
			this.showBusyIndicator();
			AuthService.getAllGroups().then(function (response) {
				this.groups = response.data;
				this.hideBusyIndicator();
			}.bind(this)).catch(function (response) {
				this.hideBusyIndicator();
			}.bind(this));
		},

		chooseRole: function (oEvent) {
			var oButton = oEvent.getSource();
			this.group = oButton.data("extraValue");
			Popup.messageBox("confirm", "Devam etmek istiyor musunuz?", function (isOk) {
				if (isOk) {
					this.updateUserRole(this.group);
				}
			}.bind(this));
		},

		updateUserRole: function (choosenGroup) {
			this.showBusyIndicator();
			var userId = AuthUtil.getUserId(),
				groupId = this.groups.find(group => group.name == choosenGroup).id;
			AuthService.addUserToGroup(groupId, userId).then(function (response) {
				this.hideBusyIndicator();
			}.bind(this)).catch(function (response) {
				this.hideBusyIndicator();
			}.bind(this));
		}

	});
});