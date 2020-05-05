sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter'
], function (BaseController, JSONModel, Device, formatter) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.maindata.controller.MaterialList", {
		formatter: formatter,

		onInit: function () {
			var materialModel = new JSONModel();
            this.setModel(materialModel, "materialModel");
		},

		createMaterial: function() {
			this.getRouter().navTo("materialDetail",
			{
				action: "create",
				id:"-1"
			});
		},
		
	});
});