sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'workerapp/services/stockservice',
	"workerapp/services/maindataservice",
	"sap/m/MessageBox",
	"sap/ui/model/Filter",
	"sap/ui/model/FilterOperator"
], function (BaseController, JSONModel, Device, formatter, stockservice, MaindataService, MessageBox, Filter, FilterOperator) {
	"use strict";
	var selectedWarehouseId,
		selectedPlantId,
		PlantData,
		selectedClientId,
		selectedMaterialId,
		selectedCompanyId;

	return BaseController.extend("workerapp.components.factory.components.stockmanagement.controller.StockTracking", {
		formatter: formatter,
		
		onInit: function () {
			var stockModel = new JSONModel();
			this.setModel(stockModel,"stockModel");
			/*this.getWarehouses();
			this.getMaterials();
			this.getStockInfo();
			this.getPlants();
			this.getClients();*/
			
			this.getRouter().getRoute("stockTracking").attachPatternMatched(this._onMaterialMatched, this);
		},
		
		_onMaterialMatched: function (oEvent) {
			this.showBusyIndicator(); //hide companyler gelince calisacak.
			this.getWarehouses();
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
				this.hideBusyIndicator();
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
			MaindataService.getWarehouses().then(function(response) {
				this.getModel("stockModel").setProperty("/warehouses", response.data);
				this.hideBusyIndicator();
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
			MaindataService.getMaterialsByPlantId(selectedPlantId).then(function(response) {
				this.getModel("stockModel").setProperty("/materials", response.data);
				this.hideBusyIndicator();	
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		
		/*getPlants: function() {
			MaindataService.getPlantByClientId(selectedClientId).then(function(response) {
				this.getModel("stockModel").setProperty("/plants", response.data);
				this.hideBusyIndicator();
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
			MaindataService.getClientInfoByCompanyId(selectedCompanyId).then(function(response) {
				this.getModel("stockModel").setProperty("/clients", response.data);
				this.hideBusyIndicator();
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
			MaindataService.getCompanies().then(function(response) {
				this.getModel("stockModel").setProperty("/companys", response.data);
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},*/

		onSelectedWarehouse: function(oEvent) {
			var selectedWarehouse = oEvent.getSource();
			selectedWarehouseId = selectedWarehouse.getSelectedKey();
			var selectedWarehouseInfo = this.getModel("stockModel").getProperty(oEvent.getSource().getSelectedItem().oBindingContexts.stockModel.sPath);
			this.getModel("stockModel").getData().selectedWarehouse = selectedWarehouseInfo;
			if(selectedWarehouseInfo.warehouseId == selectedWarehouseId) {
				selectedPlantId = selectedWarehouseInfo.plantId;
			}
			this.getModel("stockModel").refresh();
			this.getMaterials();
			this.getStockInfo();
			this.showBusyIndicator();
		},

		/*onSelectedPlant: function(oEvent) {
			var selectedPlant = oEvent.getSource();
			selectedPlantId = selectedPlant.getSelectedKey();
			this.getWarehouses();
			this.getMaterials();
			this.showBusyIndicator();
		},
		onSelectedClient: function(oEvent) {
			var selectedClient = oEvent.getSource();
			selectedClientId = selectedClient.getSelectedKey();
			this.getPlants();
			this.showBusyIndicator();
		},
		onSelectedCompany: function(oEvent) {
			var selectedCompany = oEvent.getSource();
			selectedCompanyId = selectedCompany.getSelectedKey();
			this.getClients();
		},*/

		onSelectedMaterial: function(oEvent) {
			var selectedMaterial = oEvent.getSource();
			selectedMaterialId = selectedMaterial.getSelectedKey();
		},

		saveStock: function () {
			var materialQuantity = this.getModel("stockModel").getData().materialQuantity; //selectedMaterialId = this.getModel("stockModel").getData().selectedMaterialId,

			var stockinfo = {
				"materialId": selectedMaterialId,
				"quantity": materialQuantity,
				"warehouseId": selectedWarehouseId
			  };
			stockservice.addStock(stockinfo).then(function(response) {
				console.log(response);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		extractStock: function () {
			var materialQuantity = this.getModel("stockModel").getData().materialQuantity;

			var stockinfo = {
				"materialId": selectedMaterialId,
				"quantity": materialQuantity,
				"warehouseId": selectedWarehouseId
			  };
			stockservice.exctractStock(stockinfo).then(function(response) {
				console.log(response);
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},

		/* getStockMov: function() {
			stockservice.getStockInfoByWarehouseId(selectedWarehouseId).then(function(response) {
				this.getModel("stockModel").setProperty("/stocks", response.data);
				this.getMaterialsDesc();
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				MessageBox.alert("hata", {
					icon: MessageBox.Icon.WARNING,
					title: "hata",
				});
				console.log(error);
			}.bind(this));
		},*/

		onFilterProducts: function (oEvent) {
			// add filter for search
			var aFilters = [];
			var sQuery = $.trim(oEvent.getSource().getValue());
			if (sQuery) {
				var oFilter = new Filter("materialDesc", FilterOperator.Contains, sQuery);
				aFilters.push(oFilter);
			}

			// update list binding
			var oList = this.byId("stock_table");
			var oBinding = oList.getBinding("items");
			oBinding.filter(aFilters);
		}
		/* tableFilter : function(oEvent){
			var oTable = this.getView().byId("reqListTable");
			var sValue = oEvent.getParameter("value");
			if (sValue.trim() !== "") {
				var oFilter1 = new Filter("belgeTarihi", sap.ui.model.FilterOperator.Contains, sValue);
				var oFilter2 = new Filter("bolge", sap.ui.model.FilterOperator.Contains, sValue);
				var oFilter3 = new Filter("mudur", sap.ui.model.FilterOperator.Contains, sValue);
				var oFilter4 = new Filter("requester", sap.ui.model.FilterOperator.Contains, sValue);
				var filters = [oFilter1,oFilter2,oFilter3,oFilter4];
				var finalFilter = new sap.ui.model.Filter( {
									filters : filters,
									and : false
								});
								
				oTable.getBinding("items").filter(finalFilter,
										sap.ui.model.FilterType.Application);
			}else{
				oTable.getBinding("items").filter([],
										sap.ui.model.FilterType.Application);
			}
		},*/
	});
});