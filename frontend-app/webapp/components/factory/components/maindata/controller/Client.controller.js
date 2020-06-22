sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/maindataservice",
	"sap/ui/model/json/JSONModel",
	"sap/m/Button",
	"sap/m/Dialog",
	"sap/m/Label",
	"sap/m/Input",
	"sap/ui/layout/form/SimpleForm",
	"sap/m/library"
], function (BaseController, MaindataService, JSONModel, Button, Dialog, Label, Input, SimpleForm, mobileLibrary) {
	"use strict";

	var ButtonType = mobileLibrary.ButtonType;

	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Client", {
		onInit: function () {
			var clientModel = new JSONModel({
				clientNameToCreated : ""
			});
			this.setModel(clientModel, "clientModel");
			this.getRouter().getRoute("client").attachPatternMatched(this._onMaterialMatched, this);

		},

		_onMaterialMatched: function (oEvent) {
			this.showBusyIndicator(); //hide companyler gelince calisacak.
			this.getClients();
		},

		getClients: function () {
			MaindataService.getClients().then(function (response) {
				var responseData = response.data;
				this.transformTreeData(responseData);
				this.hideBusyIndicator();
			}.bind(this)).catch(function() {
				this.hideBusyIndicator();
			}.bind(this));
		},

		getCompanies: function (callback) {
			MaindataService.getCompanies().then(function (response) {
				var responseData = response.data;
				this.getModel("clientModel").getData().companies = responseData;
				this.getModel("clientModel").refresh();
				callback(true);
				this.hideBusyIndicator();
			}.bind(this)).catch(function() {
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
				var clients;
				var plants;
				var warehouses;
				var nodeOutClient;
				var nodeOutPlant;
				var nodeOutWarehouse;
				clients = jsonData;
					if (clients) {
						for (var i = 0; i < clients.length; i++) {
							var client = clients[i];
							nodeOutClient = {
								id: client.clientId,
								text: client.clientName,
								type: "client",
								children: []
							};
							plants = client.plants;
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
									nodeOutClient.children.push(nodeOutPlant);
								}

							}
							nodes.children.push(nodeOutClient);
						}
					}
			}

			var clientData = this.getModel("clientModel").getData();
			clientData.clients = nodes;
			this.getModel("clientModel").refresh();
		},

		openClientDialog: function () {
			this.showBusyIndicator();//show companyler gelince calisacak.
			this.getCompanies(function(isOk){
				if (!this._oDialog) {
					this._oDialog = sap.ui.xmlfragment("workerapp.components.factory.components.maindata.fragment.createClientDialog", this);
					this.getView().addDependent(this._oDialog);
				 }
	
				this._oDialog.open();
			}.bind(this));
		},

		closeClientDialog: function () {
			this._oDialog.close();
		},

		createClient: function () {
			this.showBusyIndicator();
			var clientNameToCreated = this.getModel("clientModel").getData().clientNameToCreated;
			var selectedCompany = this. getModel("clientModel").getData().selectedCompany;
			var client = {
				clientName: clientNameToCreated,
				company : {
					companyId: selectedCompany
				}
			};
			this.closeClientDialog();
			MaindataService.createClient(client).then(function (response) {
				var responseData = response.data;
				console.log(responseData);
				this.getClients();
				//hideBusy clientlar geldikten sonra calisacak
			}.bind(this)).catch(function() {
				this.hideBusyIndicator();
			}.bind(this));
			console.log(clientNameToCreated);
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