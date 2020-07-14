sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/authservice",
	"sap/ui/model/json/JSONModel"
], function(BaseController, AuthService, JSONModel) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffDetail", {
		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("staffModel");

			this.oRouter.getRoute("staffDetail").attachPatternMatched(this._onMatched, this);
		},
		_onMatched: function (oEvent) {
			this.userId = oEvent.getParameter("arguments").userId;
			this.getStaff();
		},

		getStaff: function (){
            AuthService.getStaff().then(function (response) {
                var responseData = [];
				responseData.push(response.data);
				this.getModel("staffModel").getData().detailUsers = responseData;
                this.getModel("staffModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
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