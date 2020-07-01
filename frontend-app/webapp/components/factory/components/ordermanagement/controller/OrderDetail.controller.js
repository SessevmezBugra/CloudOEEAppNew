sap.ui.define([
	"sap/ui/model/json/JSONModel",
    "workerapp/base/BaseController"
], function (JSONModel, BaseController) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.ordermanagement.controller.OrderDetail", {
		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("orderModel");

			// this.oRouter.getRoute("orderDetail").attachPatternMatched(this._onProductMatched, this);
		},
		handleFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/fullScreen");
			this.oRouter.navTo("orderDetail", {layout: sNextLayout});
		},
		handleExitFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/exitFullScreen");
			this.oRouter.navTo("detail", {layout: sNextLayout, product: this._product});
		},
		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("orderList", {layout: sNextLayout});
		},
		// _onProductMatched: function (oEvent) {
		// 	this._product = oEvent.getParameter("arguments").product || this._product || "0";
		// 	this.getView().bindElement({
		// 		path: "/ProductCollection/" + this._product,
		// 		model: "products"
		// 	});
		// }
	});
}, true);
