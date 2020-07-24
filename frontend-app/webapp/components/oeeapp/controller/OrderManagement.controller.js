sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'sap/ui/core/SeparatorItem',
	"workerapp/services/maindataservice",
	"workerapp/services/stockservice",
	"workerapp/services/orderservice",
	'sap/ui/core/Fragment',
	'sap/ui/model/Filter',
	'sap/m/Token',
	'sap/ui/model/FilterOperator'
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, MaindataService, StockService, OrderService, Fragment, Filter, Token, FilterOperator) {
	"use strict";
	return BaseController.extend("workerapp.components.oeeapp.controller.OrderManagement", {
		formatter: formatter,

		onInit: function () {
            this.oRouter = this.getOwnerComponent().getRouter();
            this.getRouter().getRoute("orderManagement").attachPatternMatched(this._onMatched, this);
		},

		_onMatched: function (oEvent) {
			this.getOrders();
		},
		
		getOrders: function () {
			this.showBusyIndicator(); //hide orderlar gelince calisacak.
			OrderService.getOrdersByLoggedUser().then(function (response) {
                var responseData = response.data;
                this.getModel("orderModel").getData().orders = responseData;
                this.getModel("orderModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},

		// openCreateOrderDialog: function () {
		// 	this.showBusyIndicator();//show fabrikalar gelince calisacak.
        //     this.getPlants(function (isOk) {
		// 		if(isOk) {
		// 			if (!this._oDialog) {
		// 				this._oDialog = sap.ui.xmlfragment("createOrderDialog","workerapp.components.factory.components.ordermanagement.fragment.createOrderDialog", this);
		// 				this.getView().addDependent(this._oDialog);
		// 			}
		// 			this._oDialog.open();
		// 			// this._oDialog.byId("DTP2").setMinDate(new Date());
		// 			var date = new Date();
		// 			sap.ui.core.Fragment.byId("createOrderDialog", "DTP2").setMinDate(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
		// 			var oMultiInput = sap.ui.core.Fragment.byId("createOrderDialog", "stockMultiInput");
		// 			oMultiInput.addValidator(function(args){
		// 				var key = args.suggestionObject.getKey();
		// 				var tokens = oMultiInput.getTokens();
		// 				var isValid = true;
		// 				for(var token of tokens) {
		// 					if (token.getKey() == key){
		// 						isValid = false;
		// 					}
		// 				}
		// 				if(isValid){
		// 					return new Token({key: key, text: args.text});
		// 				}
		// 			});
		// 		}
                
        //     }.bind(this));
		// },

		// closeOrderDialog: function () {
		// 	this._oDialog.close();
		// 	this.clearOrderDialog();
		// },
		
		// clearOrderDialog: function() {
		// 	var orderDialogData = this.getModel("orderModel").getData();
		// 	orderDialogData.materialNumberToProduced = "";
		// 	orderDialogData.materialDescToProduced= "";
		// 	orderDialogData.materialQuantityToProduced= 0;
		// 	orderDialogData.plannedStartDate= new Date();
		// 	orderDialogData.plannedEndDate= new Date();
		// 	orderDialogData.customerName= "";
		// 	orderDialogData.orderNumber= "";
		// 	this.getModel("orderModel").refresh();
		// 	var oMultiInput = sap.ui.core.Fragment.byId("createOrderDialog", "stockMultiInput");
		// 	if(oMultiInput){
		// 		oMultiInput.destroyTokens();
		// 	}
		// },

		// getGroupHeader: function (oGroup) {
		// 	return new SeparatorItem( {
		// 		text: oGroup.key
		// 	});
		// },

		// getPlants: function (callback) {
        //     MaindataService.getPlants().then(function (response) {
        //         var responseData = response.data;
        //         this.getModel("orderModel").getData().plants = responseData;
        //         this.getModel("orderModel").refresh();
        //         callback(true);
        //         this.hideBusyIndicator();
        //     }.bind(this)).catch(function () {
        //         callback(false);
        //         this.hideBusyIndicator();
        //     }.bind(this));
		// },
		
		// createOrder: function () {
		// 	var orderData = this.getModel("orderModel").getData();

		// 	var order = {
		// 		plantId: orderData.selectedPlant,
		// 		orderNo: orderData.orderNumber,
		// 		customer: orderData.customerName,
		// 		plannedStartDate: orderData.plannedStartDate,
		// 		plannedEndDate: orderData.plannedEndDate,
		// 		orderedMaterial: {
		// 			materialDesc: orderData.materialDescToProduced,
		// 			materialNumber: orderData.materialNumberToProduced,
		// 			plannedProdQuantity: orderData.materialQuantityToProduced
		// 		},
		// 		consumptionStocks: [
					
		// 		]

		// 	};
		// 	var oMultiInput = sap.ui.core.Fragment.byId("createOrderDialog", "stockMultiInput");
		// 	for(var token of oMultiInput.getTokens()) {
		// 		order.consumptionStocks.push({
		// 			stockId: token.getKey()
		// 		});
		// 	}
		// 	OrderService.createOrder(order).then(function (response) {
		// 		this.closeOrderDialog();
		// 		this.hideBusyIndicator();
		// 		this.getOrders();
        //     }.bind(this)).catch(function () {
        //         // callback(false);
        //         this.hideBusyIndicator();
        //     }.bind(this));

		// },

		// onSelectedPlant: function () {
		// 	this.showBusyIndicator(); //Stoklar geldiginde hide calisacak.
		// 	this.clearOrderDialog();
		// 	this.getStocks();
		// },

		// getStocks: function () {
		// 	var selectedPlant = this.getModel("orderModel").getData().selectedPlant;
		// 	StockService.getStocksByPlantId(selectedPlant).then(function (response) {
		// 		var responseData = response.data;
		// 		this.getModel("orderModel").getData().stocks = responseData;
        //         this.getModel("orderModel").refresh();
        //         this.hideBusyIndicator();
        //     }.bind(this)).catch(function () {
        //         this.hideBusyIndicator();
        //     }.bind(this));
		// },

		// handleValueHelp: function (oEvent) {
		// 	var sInputValue = oEvent.getSource().getValue();

		// 	// create value help dialog
		// 	if (!this._valueHelpDialog) {
		// 		Fragment.load({
		// 			id: "stockHelpDialog",
		// 			name: "workerapp.components.factory.components.ordermanagement.fragment.selectStockDialog",
		// 			controller: this
		// 		}).then(function (oValueHelpDialog) {
		// 			this._valueHelpDialog = oValueHelpDialog;
		// 			this.getView().addDependent(this._valueHelpDialog);
		// 			this._openValueHelpDialog(sInputValue);
		// 		}.bind(this));
		// 	} else {
		// 		this._openValueHelpDialog(sInputValue);
		// 	}
		// },

		// _openValueHelpDialog: function (sInputValue) {
		// 	// create a filter for the binding
		// 	this._valueHelpDialog.getBinding("items").filter([new Filter(
		// 		"materialDesc",
		// 		FilterOperator.Contains,
		// 		sInputValue
		// 	)]);

		// 	// open value help dialog filtered by the input value
		// 	this._valueHelpDialog.open(sInputValue);
		// },

		// _handleValueHelpSearch: function (evt) {
		// 	var sValue = evt.getParameter("value");
		// 	var oFilter = new Filter(
		// 		"materialDesc",
		// 		FilterOperator.Contains,
		// 		sValue
		// 	);
		// 	evt.getSource().getBinding("items").filter([oFilter]);
		// },

		// _handleValueHelpClose: function (evt) {
		// 	var aSelectedItems = evt.getParameter("selectedItems"),
		// 		oMultiInput = sap.ui.core.Fragment.byId("createOrderDialog", "stockMultiInput");

		// 	if (aSelectedItems && aSelectedItems.length > 0) {
		// 		aSelectedItems.forEach(function (oItem) {
		// 			var key = oItem.getBindingContext("orderModel").getObject().stockId;
		// 			var tokens = oMultiInput.getTokens();
		// 			var isValid = true;
		// 			for(var token of tokens) {
		// 				if (token.getKey() == key){
		// 					isValid = false;
		// 				}
		// 			}
		// 			if(isValid){
		// 				oMultiInput.addToken(new Token({
		// 					text: oItem.getBindingContext("orderModel").getObject().materialDesc,
		// 					key: oItem.getBindingContext("orderModel").getObject().stockId
		// 				}));
		// 			}
		// 		});
		// 	}
		// },

		onListItemPress: function (oEvent) {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);

			var oItem = oEvent.getSource();
			var order = oItem.getBindingContext("orderModel").getObject();

			this.oRouter.navTo("orderDetail", {orderId: order.orderId, layout: oNextUIState.layout});
			
			oItem.setNavigated(true);
			var oParent = oItem.getParent();
			// store index of the item clicked, which can be used later in the columnResize event
			this.iIndex = oParent.indexOfItem(oItem);
		},
		onSearch: function (oEvent) {
			var oTableSearchState = [],
				sQuery = oEvent.getParameter("query");

			if (sQuery && sQuery.length > 0) {
				oTableSearchState = [new Filter("materialDesc", FilterOperator.Contains, sQuery)];
			}

			this.getView().byId("ordersTable").getBinding("items").filter(oTableSearchState, "Application");
		},
		
		startOrder: function (oEvent) {
			this.showBusyIndicator();
			var order = oEvent.getSource().getParent().getParent().getBindingContext("orderModel").getObject();
			var startDate = new Date();
			var orderDto = {
				orderId: order.orderId,
				actualStartDate: startDate
			}
			OrderService.startOrder(orderDto).then(function (response) {
				this.hideBusyIndicator();
				this.getOrders();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},

		holdOrder: function (oEvent) {
			this.showBusyIndicator();
			var order = oEvent.getSource().getParent().getParent().getBindingContext("orderModel").getObject();
			var endDate = new Date();
			var orderDto = {
				orderId: order.orderId,
				actualEndDate: endDate
			}
			OrderService.holdOrder(orderDto).then(function (response) {
				this.hideBusyIndicator();
				this.getOrders();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},

		resumeOrder: function (oEvent) {
			this.showBusyIndicator();
			var order = oEvent.getSource().getParent().getParent().getBindingContext("orderModel").getObject();
			var startDate = new Date();
			var orderDto = {
				orderId: order.orderId,
				actualStartDate: startDate
			}
			OrderService.resumeOrder(orderDto).then(function (response) {
				this.hideBusyIndicator();
				this.getOrders();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},

		completeOrder: function (oEvent) {
			this.showBusyIndicator();
			var order = oEvent.getSource().getParent().getParent().getBindingContext("orderModel").getObject();
			var endDate = new Date();
			var orderDto = {
				orderId: order.orderId,
				actualEndDate: endDate
			}
			OrderService.completeOrder(orderDto).then(function (response) {
				this.hideBusyIndicator();
				this.getOrders();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},
	});
});