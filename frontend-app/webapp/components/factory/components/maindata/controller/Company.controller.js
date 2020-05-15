sap.ui.define([
	"sap/ui/core/mvc/Controller",
		"sap/ui/model/json/JSONModel"
], function (Controller,JSONModel) {
	"use strict";

	return Controller.extend("workerapp.components.factory.components.maindata.controller.Company", {
		onInit: function () {
		var oData = {
				"StopReasonList": [{
					"key": "1",
					"text": "CLIENT-1",
					"nodes": [{}]
				}, {
					"key": "1",
					"text": "CLIENT-2",
					"nodes": [{}]
				},
				 {
					"key": "1",
					"text": "CLIENT-3",
					"nodes": [{}]
				}]
			};

			var oModel = new JSONModel(oData);

			var sTree = this.getView().byId("idTree");
			sTree.setModel(oModel);
		}
	});
});