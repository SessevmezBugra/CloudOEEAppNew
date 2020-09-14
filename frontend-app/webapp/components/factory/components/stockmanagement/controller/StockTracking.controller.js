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

	return BaseController.extend("workerapp.components.factory.components.stockmanagement.controller.StockTracking", {
		formatter: formatter,
		
		onInit: function () {
			var stockModel = new JSONModel();
			this.setModel(stockModel, "stockModel");			
			this.getRouter().getRoute("stockTracking").attachPatternMatched(this._onMaterialMatched, this);
		},
		
		_onMaterialMatched: function (oEvent) {
			var stockModel = new JSONModel();
			this.setModel(stockModel, "stockModel");
			this.showBusyIndicator(); //hide warehouselar gelince calisacak.
			this.getWarehouses();
		},

		onSelectedTab: function(oEvent) {
			if(!this.getModel("stockModel").getProperty("/selectedWarehouseId")){
				MessageBox.alert(this.translateText("CHOOSEWAREHOUSEMESSAGE"), {
					icon: MessageBox.Icon.INFORMATION,
					title: this.translateText("ERROR"),
				});
				return;
			}

			var selectedTab = oEvent.getParameter("key");
			if(selectedTab == "stockInfo"){
				this.showBusyIndicator();
				this.getStockInfo();
			}else if(selectedTab == "stockMov") {
				this.showBusyIndicator();
				this.getStockMovement();
			}

			
		},

		getStockInfo: function() {
			var warehouseId = this.getModel("stockModel").getProperty("/selectedWarehouseId");
			stockservice.getStockInfoByWarehouseId(warehouseId).then(function(response) {
				this.getModel("stockModel").setProperty("/stocks", response.data);
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				if(!error){
					MessageBox.alert(error, {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				console.log(error);
			}.bind(this));
		},
		
		
		getWarehouses: function() {
			MaindataService.getWarehouses().then(function(response) {
				this.getModel("stockModel").setProperty("/warehouses", response.data);
				this.hideBusyIndicator();
				MessageBox.alert(this.translateText("CHOOSEWAREHOUSEMESSAGE"), {
					icon: MessageBox.Icon.INFORMATION,
					title: this.translateText("ERROR"),
				});
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				if(!error){
					MessageBox.alert(error, {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				console.log(error);
			}.bind(this));
		},

		getMaterials: function() {
			var plantId = this.getModel("stockModel").getProperty("/selectedPlantId");
			MaindataService.getMaterialsByPlantId(plantId).then(function(response) {
				this.getModel("stockModel").setProperty("/materials", response.data);
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				if(!error){
					MessageBox.alert(error, {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				console.log(error);
			}.bind(this));
		},

		onSelectedWarehouse: function(oEvent) {
			this.showBusyIndicator();
			var selectedWarehouse = oEvent.getSource();
			var selectedWarehouseId = selectedWarehouse.getSelectedKey();
			this.getModel("stockModel").setProperty("/selectedWarehouseId", selectedWarehouseId);
			var selectedWarehouseInfo = this.getModel("stockModel").getProperty(oEvent.getSource().getSelectedItem().oBindingContexts.stockModel.sPath);
			this.getModel("stockModel").getData().selectedWarehouse = selectedWarehouseInfo;
			this.getModel("stockModel").setProperty("/selectedPlantId", selectedWarehouseInfo.plantId);
			this.getModel("stockModel").refresh();
			this.getMaterials();
			this.getStockInfo();
			this.getStockMovement();
		},

		


		onSelectedMaterial: function(oEvent) {
			this.showBusyIndicator();
			var selectedMaterial = oEvent.getSource();
			var selectedMaterialId = selectedMaterial.getSelectedKey();
			var selectedWarehouseId = this.getModel("stockModel").getData().selectedWarehouseId;
			
			stockservice.getStockInfoByWarehouseIdAndMaterialId(selectedWarehouseId, selectedMaterialId).then(function(response) {
				this.getModel("stockModel").getData().currentStock = response.data.quantity;
				this.getModel("stockModel").refresh();
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
			}.bind(this));
			
		},

		saveStock: function () {
			this.showBusyIndicator();
			var materialQuantity = this.getModel("stockModel").getData().materialQuantity;
			var warehouseId = this.getModel("stockModel").getData().selectedWarehouseId;
			var materialId = this.getModel("stockModel").getData().selectedMaterialId;
			var stockinfo = {
				"materialId": materialId,
				"quantity": materialQuantity,
				"warehouseId": warehouseId
			  };
			if(!materialQuantity || !warehouseId || !materialId || materialQuantity == " " || warehouseId == " " || materialId == " "){
				if(materialQuantity == 0){
					this.hideBusyIndicator();
					MessageBox.alert(this.translateText("MESSAGEERRORZERO"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					this.hideBusyIndicator();
					MessageBox.alert(this.translateText("MESSAGEERROREMPTY"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
			}
			else{
				stockservice.addStock(stockinfo).then(function(response) {
					this.hideBusyIndicator();
					console.log(response);
					this.clearStock();
					MessageBox.alert(this.translateText("MESSAGEADD"), {
						icon: MessageBox.Icon.INFORMATION,
						title: this.translateText("INFORMATION"),
					});
				}.bind(this)).catch(function(error) {
					this.hideBusyIndicator();
					if(!error){
						MessageBox.alert(error, {
							icon: MessageBox.Icon.WARNING,
							title: this.translateText("ERROR"),
						});
					}
					else {
						MessageBox.alert(this.translateText("MESSAGEERRORADD"), {
							icon: MessageBox.Icon.WARNING,
							title: this.translateText("ERROR"),
						});
					}
					console.log(error);
				}.bind(this));
			}
		},

		clearStock: function(){
			this.getModel("stockModel").setProperty("/currentStock", null);
			this.getModel("stockModel").setProperty("/materialQuantity", null);
			this.getModel("stockModel").setProperty("/selectedMaterialId", null);
			this.getModel("stockModel").refresh();
		},

		extractStock: function () {
			this.showBusyIndicator();
			var materialQuantity = this.getModel("stockModel").getData().materialQuantity;
			var warehouseId = this.getModel("stockModel").getData().selectedWarehouseId;
			var materialId = this.getModel("stockModel").getData().selectedMaterialId;
			var stockinfo = {
				"materialId": materialId,
				"quantity": materialQuantity,
				"warehouseId": warehouseId
			  };
			if(!materialQuantity || !warehouseId || !materialId || materialQuantity == " " || warehouseId == " " || materialId == " "){
				if(materialQuantity == 0){
					this.hideBusyIndicator();
					MessageBox.alert(this.translateText("MESSAGEERRORZERO"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					this.hideBusyIndicator();
					MessageBox.alert(this.translateText("MESSAGEERROREMPTY"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
			}
			else{
				stockservice.exctractStock(stockinfo).then(function(response) {
					this.hideBusyIndicator();
					console.log(response);
					this.clearStock();
					MessageBox.alert(this.translateText("MESSAGEEXTRACT"), {
						icon: MessageBox.Icon.INFORMATION,
						title: this.translateText("INFORMATION"),
					});
				}.bind(this)).catch(function(error) {
					this.hideBusyIndicator();
					if(!error){
						MessageBox.alert(error, {
							icon: MessageBox.Icon.WARNING,
							title: this.translateText("ERROR"),
						});
					}
					else {
						MessageBox.alert(this.translateText("MESSAGEERROREXTRACT"), {
							icon: MessageBox.Icon.WARNING,
							title: this.translateText("ERROR"),
						});
					}
					console.log(error);
				}.bind(this));
			}
		},

		onFilterProducts: function (oEvent) {
			// add filter for search
			var aFilters = [];
			var sQuery = $.trim(oEvent.getSource().getValue());
			if (sQuery) {
				var oFilter = new Filter("materialDesc", FilterOperator.Contains, sQuery);
				aFilters.push(oFilter);
			}

			// update list binding
			var oList = this.byId("stockTable");
			var oBinding = oList.getBinding("items");
			oBinding.filter(aFilters);
		},

		getStockMovement: function() {
			var warehouseId = this.getModel("stockModel").getProperty("/selectedWarehouseId");
			stockservice.getStockMovByWarehouseId(warehouseId).then(function(response) {
				this.getModel("stockModel").setProperty("/stockmovement", response.data.reverse());
				this.hideBusyIndicator();
			}.bind(this)).catch(function(error) {
				this.hideBusyIndicator();
				if(!error){
					MessageBox.alert(error, {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				else {
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
				console.log(error);
			}.bind(this));
		},

		onFilterMov: function (oEvent) {
			// add filter for search
			var aFilters = [];
			var sQuery = $.trim(oEvent.getSource().getValue());
			if (sQuery) {
				var oFilter = new Filter("materialDesc", FilterOperator.Contains, sQuery);
				aFilters.push(oFilter);
			}

			// update list binding
			var oList = this.byId("movementTable");
			var oBinding = oList.getBinding("items");
			oBinding.filter(aFilters);
		}
	});
});