sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	"workerapp/services/orderservice"
], function (BaseController, JSONModel, Device, formatter, OrderService) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.fastmenu.controller.Home", {
		formatter: formatter,

		onInit: function () {
			var oViewModel = new JSONModel({
				isPhone : Device.system.phone
			});
			this.setModel(oViewModel, "fastMenuModel");
			Device.media.attachHandler(function (oDevice) {
				this.getModel("fastMenuModel").setProperty("/isPhone", oDevice.name === "Phone");
			}.bind(this));
			this.getRouter().getRoute("factory").attachPatternMatched(this._onMatched, this);
		},

		_onMatched: function (oEvent) {
			this.getOrders();
		},
		
		getOrders: function () {
			// this.showBusyIndicator(); //hide orderlar gelince calisacak.
			OrderService.getOrdersByLoggedUser().then(function (response) {
                var responseData = response.data;
                this.getModel("fastMenuModel").getData().orders = responseData;
                this.getModel("fastMenuModel").refresh();
                // this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                // this.hideBusyIndicator();
            }.bind(this));
		},
		goToTargetMenu: function(oEvent) {
			var target = oEvent.getSource().data("target");
			this.getParentComponent().getRouter().navTo(target);
		}
	});
});