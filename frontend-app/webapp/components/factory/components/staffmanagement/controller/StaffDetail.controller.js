sap.ui.define([
	"workerapp/base/BaseController",
], function(
	BaseController
) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffDetail", {
		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("staffModel");

			// this.oRouter.getRoute("orderDetail").attachPatternMatched(this._onProductMatched, this);
		},
		handleFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/fullScreen");
			this.oRouter.navTo("staffDetail", {layout: sNextLayout});
		},
		handleExitFullScreen: function () {
			var sNextLayout = this.oModel.getProperty("/actionButtonsInfo/midColumn/exitFullScreen");
			this.oRouter.navTo("detail", {layout: sNextLayout, product: this._product});
		},
		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("staffTracking", {layout: sNextLayout});
		},
		// _onProductMatched: function (oEvent) {
		// 	this._product = oEvent.getParameter("arguments").product || this._product || "0";
		// 	this.getView().bindElement({
		// 		path: "/ProductCollection/" + this._product,
		// 		model: "products"
		// 	});
		// }
	});
});