sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/companyservice",
	"sap/ui/model/json/JSONModel"
], function (BaseController, CompanyService, JSONModel) {
	"use strict";
	var response;
	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Plant", {
		onInit: function () {
			this.getRouter().getRoute("plant").attachPatternMatched(this._onPlantMatched, this);
		},
		_onPlantMatched: function (oEvent) {
			var id = oEvent.getParameter("arguments").id;
			var self = this;
			CompanyService.getPlantInfo(parseInt(id)).then(function (response) {
                var oModel = new JSONModel();
                oModel.setData({
                    plantInfo: response.data
                });
                self.getView().setModel(oModel);
			}); 
		}
	});
});