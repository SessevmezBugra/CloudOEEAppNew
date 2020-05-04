sap.ui.define([
	"workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel"
], function (BaseController, JSONModel) {
	"use strict";

	return BaseController.extend("workerapp.controller.App", {

		onInit : function () {
			this.getView().addStyleClass(this.getOwnerComponent().getContentDensityClass());
		}

	});
});