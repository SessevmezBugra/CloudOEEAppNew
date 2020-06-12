sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'workerapp/components/factory/components/stockmanagement/services/stockservice',
	"sap/m/MessageBox"
], function (BaseController, JSONModel, Device, formatter, stockservice,MessageBox) {
	"use strict";
	var selectedWarehouseId,
		selectedPlantId,
		selectedClientId,
		selectedCompanyId;

	return BaseController.extend("workerapp.components.factory.components.stockmanagement.controller.StockTracking", {
		formatter: formatter,
		
		onInit: function () {
			var stockModel = new JSONModel();
			this.setModel(stockModel,"stockModel");
			this.getWarehouses();
			this.getMaterials();
			this.getStockInfo();
			this.getPlants();
			this.getClients();
			this.getCompanys();
		},

		onSelectedTab: function(oEvent) {
			this.getWarehouses();
			this.getMaterials();
			this.getStockInfo();

		},

		getMaterialsDesc: function() {
			var stockData = this.getModel("stockModel").getProperty("/stocks");
			var materialData = this.getModel("stockModel").getProperty("/materials");
			for(var stock of stockData){
				for(var material of materialData){
					if(stock.materialId == material.materialId) {
						stock.materialDesc = material.materialDesc;
					}
				}
			}
			this.getModel("stockModel").refresh();
		},

		getStockInfo: function() {
			stockservice.getStockInfoByWarehouseId(selectedWarehouseId).then(function(response) {
				this.getModel("stockModel").setProperty("/stocks", response.data);
				this.getMaterialsDesc();
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
			stockservice.getWarehousesByPlantId(selectedPlantId).then(function(response) {
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
			stockservice.getMaterialsByPlantId(selectedPlantId).then(function(response) {
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

		
		getPlants: function() {
			stockservice.getPlantByClientId(selectedClientId).then(function(response) {
				this.getModel("stockModel").setProperty("/plants", response.data);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		getClients: function() {
			stockservice.getClientInfoByCompanyId(1).then(function(response) {
				this.getModel("stockModel").setProperty("/clients", response.data);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		getCompanys: function() {
			stockservice.getCompanyInfoById(1).then(function(response) {
				this.getModel("stockModel").setProperty("/companys", response.data);
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
			this.getStockInfo();
		},
		onSelectedPlant: function(oEvent) {
			var selectedPlant = oEvent.getSource();
			selectedPlantId = selectedPlant.getSelectedKey();
			this.getWarehouses();
			this.getMaterials();
		},
		onSelectedClient: function(oEvent) {
			var selectedClient = oEvent.getSource();
			selectedClientId = selectedClient.getSelectedKey();
			this.getPlants();
		},
		onSelectedCompany: function(oEvent) {
			var selectedCompany = oEvent.getSource();
			selectedCompanyId = selectedCompany.getSelectedKey();
			//this.getClients();
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