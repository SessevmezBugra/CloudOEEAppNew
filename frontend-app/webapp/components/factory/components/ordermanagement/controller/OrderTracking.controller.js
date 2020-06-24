sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'sap/ui/core/SeparatorItem',
	"workerapp/services/maindataservice",
	"workerapp/services/stockservice"
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, MaindataService, StockService) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.ordermanagement.controller.OrderTracking", {
		formatter: formatter,

		onInit: function () {

			var orderModel = new JSONModel({
				materialNumberToProduced:"",
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
                    this._oDialog = sap.ui.xmlfragment("createOrderDialog","workerapp.components.factory.components.ordermanagement.fragment.createOrderDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
				this._oDialog.open();
				// this._oDialog.byId("DTP2").setMinDate(new Date());
				var date = new Date();
				sap.ui.core.Fragment.byId("createOrderDialog", "DTP2").setMinDate(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
            }.bind(this));
		},

		closeOrderDialog: function () {
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
		
		createOrder: function () {
			var orderData = this.getModel("orderModel").getData();

		},

		onSelectedPlant: function () {
			this.showBusyIndicator(); //Stoklar geldiginde hide calisacak.
			this.getStocks();
		},

		getStocks: function () {
			var selectedPlant = this.getModel("orderModel").getData().selectedPlant;
			StockService.getStocksByPlantId(selectedPlant).then(function (response) {
				var responseData = response.data;
				this.getModel("orderModel").getData().stocks = responseData;
                this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		}

	});
});