sap.ui.define([
    "sap/ui/model/json/JSONModel",
    "workerapp/base/BaseController",
    "workerapp/services/orderservice",
    "workerapp/services/confirmationservice",
    'sap/ui/model/Filter',
    'sap/ui/model/FilterOperator',
    "workerapp/services/maindataservice",
	'sap/ui/core/Fragment',
	'sap/m/Token'
], function (JSONModel, BaseController, OrderService, ConfirmationService, Filter, FilterOperator, MainDataService, Fragment, Token) {
    "use strict";

    return BaseController.extend("workerapp.components.oeeapp.controller.ReportProduction", {
        onInit: function () {
            var reportProductionModel = new JSONModel();
            this.setModel(reportProductionModel, "reportProductionModel");

            this.getRouter().getRoute("reportProduction").attachPatternMatched(this._onMatched, this);
            
        },
        _onMatched: function (oEvent) {
            // this.getActiveOrders();
        },

        getActiveOrders: function (callback) {
            OrderService.getActiveOrders().then(function (response) {
                this.getModel("reportProductionModel").getData().orders = response.data;
                this.getModel("reportProductionModel").refresh();
                this.hideBusyIndicator();
                callback(true);
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
        },


        openOrderListDialog: function () {
            this.showBusyIndicator();//show fabrikalar gelince calisacak.
            this.getActiveOrders(function (isOk) {
                if (isOk) {
                    if (!this._oDialog) {
                        this._oDialog = sap.ui.xmlfragment("orderListDialog", "workerapp.components.oeeapp.fragment.orderListDialog", this);
                        this.getView().addDependent(this._oDialog);
                    }
                    this._oDialog.open();
                }

            }.bind(this));
        },

        closeOrderDialog: function (oEvent) {
            var aContexts = oEvent.getParameter("selectedContexts");
            if (aContexts && aContexts.length) {
                this.orderId = aContexts[0].getObject().orderId;
                this.plantId = aContexts[0].getObject().plantId;
                this.getOrderInfo();
                this.getQualityTypes();
                this.getReasonCodes();
            } else {
                MessageToast.show("Aktif bir siparis seciniz.");
            }
            oEvent.getSource().getBinding("items").filter([]);
            this.clearOrderDialog();
        },

        clearOrderDialog: function () {
            var orderDialogData = this.getModel("reportProductionModel").getData();
            orderDialogData.order = [];
            this.getModel("orderModel").refresh();
        },

        handleSearch: function (oEvent) {
            var sValue = oEvent.getParameter("value");
            var oFilter = new Filter("materialDesc", FilterOperator.Contains, sValue);
            var oBinding = oEvent.getSource().getBinding("items");
            oBinding.filter([oFilter]);
        },

        getOrderInfo: function () {
            this.showBusyIndicator();
            OrderService.getOrderInfoById(this.orderId).then(function (response) {
                this.getModel("reportProductionModel").getData().selectedOrder = response.data;
                this.getModel("reportProductionModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        getQualityTypes: function() {
            this.showBusyIndicator();
            MainDataService.getQualityTypesByPlantId(this.plantId).then(function (response) {
                this.getModel("reportProductionModel").getData().qualityTypes = response.data;
                this.getModel("reportProductionModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        getReasonCodes: function() {
            this.showBusyIndicator();
            MainDataService.getReasonCodesByPlantId(this.plantId).then(function (response) {
                this.getModel("reportProductionModel").getData().reasonCodes = response.data;
                this.getModel("reportProductionModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        handleValueHelp: function (oEvent) {
			var sInputValue = oEvent.getSource().getValue();
            this.oMultiInput = this.byId(oEvent.getParameter("id"));
            this.oMultiInput.addValidator(function(args){
                var key = args.suggestionObject.getKey();
                var tokens = this.oMultiInput.getTokens();
                var isValid = true;
                for(var token of tokens) {
                    if (token.getKey() == key){
                        isValid = false;
                    }
                }
                if(isValid){
                    return new Token({key: key, text: args.text});
                }
            }.bind(this));
			// create value help dialog
			if (!this._valueHelpDialog) {
				Fragment.load({
					id: "reasonCodeHelpDialog",
					name: "workerapp.components.oeeapp.fragment.selectReasonCodeDialog",
					controller: this
				}).then(function (oValueHelpDialog) {
					this._valueHelpDialog = oValueHelpDialog;
					this.getView().addDependent(this._valueHelpDialog);
					this._openValueHelpDialog(sInputValue);
				}.bind(this));
			} else {
				this._openValueHelpDialog(sInputValue);
			}
		},

		_openValueHelpDialog: function (sInputValue) {
			// create a filter for the binding
			this._valueHelpDialog.getBinding("items").filter([new Filter(
				"reasonCode",
				FilterOperator.Contains,
				sInputValue
			)]);

			// open value help dialog filtered by the input value
			this._valueHelpDialog.open(sInputValue);
		},

		_handleValueHelpSearch: function (evt) {
			var sValue = evt.getParameter("value");
			var oFilter = new Filter(
				"reasonCode",
				FilterOperator.Contains,
				sValue
			);
			evt.getSource().getBinding("items").filter([oFilter]);
		},

		_handleValueHelpClose: function (evt) {
            var aSelectedItems = evt.getParameter("selectedItems");

			if (aSelectedItems && aSelectedItems.length > 0) {
				aSelectedItems.forEach(function (oItem) {
					var key = oItem.getBindingContext("reportProductionModel").getObject().reasonCode;
					var tokens = this.oMultiInput.getTokens();
					var isValid = true;
					for(var token of tokens) {
						if (token.getKey() == key){
							isValid = false;
						}
					}
					if(isValid){
						this.oMultiInput.addToken(new Token({
							text: oItem.getBindingContext("reportProductionModel").getObject().reasonDesc,
							key: oItem.getBindingContext("reportProductionModel").getObject().reasonCode
						}));
					}
				}.bind(this));
			}
		},
    });
}, true);
