sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'workerapp/components/factory/components/stockmanagement/services/stockservice'
], function (BaseController, JSONModel, Device, formatter, stockservice) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.stockmanagement.controller.StockTracking", {
		formatter: formatter,

		onInit: function () {
			
		},

		saveStock: function () {
			var stockinfo = {
				"materialId": 32,
				quantity: "",
				"stockId": 0,
				"stockMovements": [
				  { 
					quantity: "",
					"stockMovId": 32
				  }
				],
				"warehouseId": 1 
			  };
			  	
			stockservice.createStockInfo(stockinfo).then(function(response) {
				console.log(response);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		}
	});
});