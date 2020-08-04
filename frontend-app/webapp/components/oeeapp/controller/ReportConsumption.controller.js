sap.ui.define([
    "sap/ui/model/json/JSONModel",
    "workerapp/base/BaseController",
    "workerapp/services/orderservice",
    "workerapp/services/confirmationservice",
    'sap/ui/model/Filter',
    'sap/ui/model/FilterOperator',
	"sap/m/MessageToast",
], function (JSONModel, BaseController, OrderService, ConfirmationService, Filter, FilterOperator, MessageToast) {
    "use strict";

    return BaseController.extend("workerapp.components.oeeapp.controller.ReportConsumption", {
        onInit: function () {
            var reportConsumptionModel = new JSONModel();
            this.setModel(reportConsumptionModel, "reportConsumptionModel");

            this.getRouter().getRoute("reportConsumption").attachPatternMatched(this._onMatched, this);
        },
        _onMatched: function (oEvent) {
            // this.getActiveOrders();
        },

        getActiveOrders: function (callback) {
            OrderService.getActiveOrders().then(function (response) {
                this.getModel("reportConsumptionModel").getData().orders = response.data;
                this.getModel("reportConsumptionModel").refresh();
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
                this.getOrderInfo();
            } else {
                MessageToast.show("Aktif bir siparis seciniz.");
            }
            oEvent.getSource().getBinding("items").filter([]);
            this.clearOrderDialog();
        },

        clearOrderDialog: function () {
            var orderDialogData = this.getModel("reportConsumptionModel").getData();
            orderDialogData.order = [];
            this.getModel("orderModel").refresh();
        },

        handleSearch: function (oEvent) {
            var sValue = oEvent.getParameter("value");
            var oFilter = new Filter("materialDesc", FilterOperator.Contains, sValue);
            var oBinding = oEvent.getSource().getBinding("items");
            oBinding.filter([oFilter]);
        },

        getOrderInfo: function (callback) {
            this.showBusyIndicator();
            OrderService.getOrderInfoById(this.orderId).then(function (response) {
                this.getModel("reportConsumptionModel").getData().selectedOrder = response.data;
                this.getModel("reportConsumptionModel").refresh();
                this.hideBusyIndicator();
                callback(true);
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
        }
    });
}, true);
