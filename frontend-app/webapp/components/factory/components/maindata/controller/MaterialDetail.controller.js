sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'../model/formatter'
], function (BaseController, JSONModel, Device, formatter) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.maindata.controller.MaterialDetail", {
		formatter: formatter,

		onInit: function () {
			this.getRouter().getRoute("materialDetail").attachPatternMatched(this._onMaterialMatched, this);
		},

		_onMaterialMatched : function (oEvent) {
			var materialModel = new JSONModel();
			this.setModel(materialModel, "materialModel");

			var action =  oEvent.getParameter("arguments").action;
			this.getModel("materialModel").getData().action = action;
			this.getModel("materialModel").refresh();
		},

		editMaterial: function() {
			this.getModel("materialModel").getData().action = "edit";
			this.getModel("materialModel").refresh();
		},

		cancelChanges: function() {
			this.getRouter().navTo("materialList");
		},

		saveMaterial: function() {
			var material = this.getModel("materialModel").getData().material;
		}
	});
});