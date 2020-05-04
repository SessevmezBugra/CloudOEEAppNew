sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter'
], function (BaseController, JSONModel, Device, formatter) {
	"use strict";
	return BaseController.extend("workerapp.components.home.components.staffmanagement.controller.Home", {
		formatter: formatter,

		onInit: function () {
			var oViewModel = new JSONModel({
				isPhone : Device.system.phone
			});
			this.setModel(oViewModel, "view");
			Device.media.attachHandler(function (oDevice) {
				this.getModel("view").setProperty("/isPhone", oDevice.name === "Phone");
			}.bind(this));
		},

		goToTargetMenu: function(oEvent) {
			var target = oEvent.getSource().data("target");
			this.getParentComponent().getRouter().navTo(target);
		}
	});
});