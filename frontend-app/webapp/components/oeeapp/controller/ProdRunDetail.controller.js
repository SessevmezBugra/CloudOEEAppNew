sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/components/oeeapp/model/formatter',
	'sap/ui/core/SeparatorItem',
	"workerapp/services/confirmationservice",
	"workerapp/services/stockservice",
	"workerapp/services/orderservice",
	'sap/ui/core/Fragment',
	'sap/ui/model/Filter',
	'sap/m/Token',
	'sap/ui/model/FilterOperator'
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, ConfirmationService, StockService, OrderService, Fragment, Filter, Token, FilterOperator) {
	"use strict";
	return BaseController.extend("workerapp.components.oeeapp.controller.ProdRunDetail", {
		formatter: formatter,

		onInit: function () {
			this.oRouter = this.getOwnerComponent().getRouter();
			this.getRouter().getRoute("prodRunDetail").attachPatternMatched(this._onMatched, this);
		},

		_onMatched: function (oEvent) {
			this.orderId = oEvent.getParameter("arguments").orderId;
			this.runId = oEvent.getParameter("arguments").runId;
			this.getProdRunData();
			this.getConsumptionData();
		},

		getProdRunData: function () {
			this.getModel("orderModel").getData().prodRunDataTableBusy = true;
			this.getModel("orderModel").refresh();
			ConfirmationService.getProdRunDataByRunId(this.runId).then(function (response) {
				this.getModel("orderModel").getData().prodRunDatas = response.data;
				this.getModel("orderModel").getData().prodRunDataTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("orderModel").getData().prodRunDataTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		getConsumptionData: function () {
			this.getModel("orderModel").getData().consumptionDataTableBusy = true;
			this.getModel("orderModel").refresh();
			ConfirmationService.getConsumptionDataByRunId(this.runId).then(function (response) {
				this.getModel("orderModel").getData().consumptionDatas = response.data;
				this.getModel("orderModel").getData().consumptionDataTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("orderModel").getData().consumptionDataTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		handleClose: function () {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
			this.oRouter.navTo("orderDetail", { orderId: this.orderId, layout: oNextUIState.layout });
		},
		
	});
});