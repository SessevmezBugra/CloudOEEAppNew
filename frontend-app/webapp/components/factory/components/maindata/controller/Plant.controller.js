sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/maindataservice",
	"sap/ui/model/json/JSONModel"
], function (BaseController, MaindataService, JSONModel) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Plant", {
		onInit: function () {
			var plantModel = new JSONModel({
				plantNameToCreated: "",
				selectedClient: null
			});
			this.setModel(plantModel, "plantModel");
			this.getRouter().getRoute("plant").attachPatternMatched(this._onMaterialMatched, this);

		},

		_onMaterialMatched: function (oEvent) {
			this.showBusyIndicator(); //hide companyler gelince calisacak.
			this.getPlants();
		},

		getPlants: function () {
			MaindataService.getPlants().then(function (response) {
				var responseData = response.data;
				this.transformTreeData(responseData);
				this.hideBusyIndicator();
			}.bind(this)).catch(function () {
				this.hideBusyIndicator();
			}.bind(this));
		},

		getClients: function (callback) {
			MaindataService.getClients().then(function (response) {
				var responseData = response.data;
				this.getModel("plantModel").getData().clients = responseData;
				this.getModel("plantModel").refresh();
				callback(true);
				this.hideBusyIndicator();
			}.bind(this)).catch(function () {
				callback(false);
				this.hideBusyIndicator();
			}.bind(this));
		},

		transformTreeData: function (jsonData) {
			var nodes = {
				// id: jsonData.companyId,
				// text: jsonData.companyName,
				children: []
			};
			if (jsonData != null && jsonData.length > 0) {
				var plants;
				var warehouses;
				var nodeOutPlant;
				var nodeOutWarehouse;
				plants = jsonData;

				if (plants && plants.length > 0) {
					for (var j = 0; j < plants.length; j++) {
						var plant = plants[j];
						nodeOutPlant = {
							id: plant.plantId,
							text: plant.plantName,
							type: "plant",
							children: []
						};
						warehouses = plant.warehouses;
						for (var k = 0; k < warehouses.length; k++) {
							var warehouse = warehouses[k];
							nodeOutWarehouse = {
								id: warehouse.warehouseId,
								text: warehouse.warehouseName,
								type: "warehouse",
								children: []
							};
							nodeOutPlant.children.push(nodeOutWarehouse);
						}
						nodes.children.push(nodeOutPlant);
					}

				}
			}

			var plantData = this.getModel("plantModel").getData();
			plantData.plants = nodes;
			this.getModel("plantModel").refresh();
		},

		openPlantDialog: function () {
			this.showBusyIndicator();//show companyler gelince calisacak.
			this.getClients(function (isOk) {
				if (!this._oDialog) {
					this._oDialog = sap.ui.xmlfragment("workerapp.components.factory.components.maindata.fragment.createPlantDialog", this);
					this.getView().addDependent(this._oDialog);
				}

				this._oDialog.open();
			}.bind(this));
		},

		closePlantDialog: function () {
			this._oDialog.close();
		},

		createPlant: function () {
			this.showBusyIndicator();
			var plantNameToCreated = this.getModel("plantModel").getData().plantNameToCreated;
			var selectedClient = this.getModel("plantModel").getData().selectedClient;
			var plant = {
				plantName: plantNameToCreated,
				client: {
					clientId: selectedClient
				}
			};
			this.closePlantDialog();
			MaindataService.createPlant(plant).then(function (response) {
				var responseData = response.data;
				console.log(responseData);
				this.getPlants();
				//hideBusy clientlar geldikten sonra calisacak
			}.bind(this)).catch(function () {
				this.hideBusyIndicator();
			}.bind(this));
		},

		onSelectedItem: function (oEvent) {
			var item = oEvent.getSource().getSelectedItem().getBindingContext().getObject();
			var id = item.id;
			var type = item.type;
			if (type === "client") {
				this.getRouter().navTo("client",
					{
						id: id
					});
			} else if (type === "plant") {
				this.getRouter().navTo("plant",
					{
						id: id
					});
			}
			else {
				this.getRouter().navTo("client",
					{
						id: id
					});
			}
		}
	});
});