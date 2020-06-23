sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'sap/ui/core/SeparatorItem',
	"workerapp/services/maindataservice"
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, MaindataService) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.ordermanagement.controller.OrderTracking", {
		formatter: formatter,

		onInit: function () {
			var orderModel = new JSONModel({
				materialDescToProduced: "",
				materialQuantityToProduced: 0,
				plannedStartDate: new Date(),
				plannedEndDate: new Date(),
				customerName: ""
            });
            this.setModel(orderModel, "orderModel");
            this.getRouter().getRoute("orderTracking").attachPatternMatched(this._onMaterialMatched, this);
		},

		_onMaterialMatched: function (oEvent) {
            // this.showBusyIndicator(); //hide companyler gelince calisacak.
        },

		openCreateOrderDialog: function () {
			this.showBusyIndicator();//show companyler gelince calisacak.
            this.getPlants(function (isOk) {
                if (!this._oDialog) {
                    this._oDialog = sap.ui.xmlfragment("workerapp.components.factory.components.ordermanagement.fragment.createOrderDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
                this._oDialog.open();
            }.bind(this));
		},

		closeMaterialDialog: function () {
            this._oDialog.close();
        },

		getGroupHeader: function (oGroup) {
			return new SeparatorItem( {
				text: oGroup.key
			});
		},

		getPlants: function (callback) {
            MaindataService.getPlants().then(function (response) {
                var responseData = response.data;
                this.getModel("orderModel").getData().plants = responseData;
                this.getModel("orderModel").refresh();
                callback(true);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
        },

	});
});