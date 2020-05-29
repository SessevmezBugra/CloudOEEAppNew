sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'workerapp/components/factory/components/stockmanagement/services/stockservice'
], function (BaseController, JSONModel, Device, formatter, stockservice) {
	"use strict";
	var selectedWarehouseId;

	return BaseController.extend("workerapp.components.factory.components.stockmanagement.controller.StockTracking", {
		formatter: formatter,
		
		onInit: function () {
			var stockModel = new JSONModel();
			this.setModel(stockModel,"stockModel");
			this.getWarehouses();
			this.getMaterials();
			this.getStockInfo();
		},

		onSelectedTab: function(oEvent) {
			this.getWarehouses();
			this.getMaterials();
		},

		getStockInfo: function() {
			stockservice.getStockInfoByWarehouseId(selectedWarehouseId).then(function(response) {
				this.getModel("stockModel").setProperty("/stocks", response.data);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		getWarehouses: function() {
			stockservice.getWarehousesByPlantId(16).then(function(response) {
				this.getModel("stockModel").setProperty("/warehouses", response.data);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		getMaterials: function() {
			stockservice.getMaterialsByPlantId(16).then(function(response) {
				this.getModel("stockModel").setProperty("/materials", response.data);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		onSelectedWarehouse: function(oEvent) {
			var selectedWarehouse = oEvent.getSource();
			selectedWarehouseId = selectedWarehouse.getSelectedKey();
		},

		saveStock: function () {
			var selectedMaterialId = this.getModel("stockModel").getData().selectedMaterialId,
				materialQuantity = this.getModel("stockModel").getData().materialQuantity;

			var stockinfo = {
				"materialId": selectedMaterialId,
				"quantity": materialQuantity,
				"warehouseId": selectedWarehouseId
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