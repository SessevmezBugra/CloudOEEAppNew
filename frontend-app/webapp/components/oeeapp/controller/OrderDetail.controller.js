sap.ui.define([
	"sap/ui/model/json/JSONModel",
	"workerapp/base/BaseController",
	"workerapp/services/orderservice",
	"workerapp/services/confirmationservice",
	'workerapp/components/oeeapp/model/formatter',
], function (JSONModel, BaseController, OrderService, ConfirmationService, formatter) {
	"use strict";

	return BaseController.extend("", {
		
		formatter: formatter,

		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("orderModel");

			this.oRouter.getRoute("orderDetail").attachPatternMatched(this._onMatched, this);
		},
		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("orderList", {layout: sNextLayout});
		},
		
		_onMatched: function (oEvent) {
			this.orderId = oEvent.getParameter("arguments").orderId;
			this.getProducedMaterial();
			this.getConsumptionStocks();
			this.getProdRuns();
		},

		getProducedMaterial: function() {
			this.getModel("orderModel").getData().producedMaterialTableBusy = true;
			OrderService.getProducedMaterialByOrderId(this.orderId).then(function (response) {
				var responseData = [];
				responseData.push(response.data);
				this.getModel("orderModel").getData().producedMaterials = responseData;
				this.getModel("orderModel").getData().producedMaterialTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("orderModel").getData().producedMaterialTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		getConsumptionStocks: function() {
			this.getModel("orderModel").getData().consumptionStockTableBusy = true;
			OrderService.getConsumptionStockByOrderId(this.orderId).then(function (response) {
                var responseData = response.data;
                this.getModel("orderModel").getData().consumptionStocks = responseData;
				this.getModel("orderModel").getData().consumptionStockTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("orderModel").getData().consumptionStockTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		getProdRuns: function() {
			this.getModel("orderModel").getData().prodRunsTableBusy = true;
			ConfirmationService.getProdRunByOrderId(this.orderId).then(function (response) {
                var responseData = response.data;
				this.getModel("orderModel").getData().prodRuns = responseData;
				this.getModel("orderModel").getData().prodRunsTableBusy = false;
                this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("orderModel").getData().prodRunsTableBusy = false;
				this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		onPressProdRunDetail: function(oEvent) {
			this.getOwnerComponent().getModel("oeeGlobalModel").getData().sideNavigationExpanded = false;
			this.getOwnerComponent().getModel("oeeGlobalModel").refresh();
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(2);

			var oItem = oEvent.getSource();
			var prodRunHdr = oItem.getBindingContext("orderModel").getObject();

			this.oRouter.navTo("prodRunDetail", { orderId: this.orderId, runId: prodRunHdr.runId, layout: oNextUIState.layout });

			oItem.setNavigated(true);
			var oParent = oItem.getParent();
			// store index of the item clicked, which can be used later in the columnResize event
			this.iIndex = oParent.indexOfItem(oItem);
		}
	});
}, true);
