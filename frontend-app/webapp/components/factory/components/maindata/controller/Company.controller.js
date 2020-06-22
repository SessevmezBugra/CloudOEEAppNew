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

	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Company", {
		onInit: function () {
			var companyModel = new JSONModel({
				companyNameToCreated : ""
			});
			this.setModel(companyModel, "companyModel");
			this.getRouter().getRoute("company").attachPatternMatched(this._onMaterialMatched, this);

		},

		_onMaterialMatched: function (oEvent) {
			this.showBusyIndicator(); //hide companyler gelince calisacak.
			this.getCompanies();
		},

		getCompanies: function () {
			MaindataService.getCompanies().then(function (response) {
				var responseData = response.data;
				this.transformTreeData(responseData);
				this.hideBusyIndicator();
			}.bind(this)).catch(function() {
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
				var nodeOut;
				var clients;
				var plants;
				var warehouses;
				var nodeOutClient;
				var nodeOutPlant;
				var nodeOutWarehouse;
				var company;
				for (company of jsonData) {
					nodeOut = {
						id: company.companyId,
						text: company.companyName,
						type: "company",
						children: []
					};
					var clients = company.clients;
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
							// else {
							// 	nodes.push(nodeOutClient);
							// }
							nodeOut.children.push(nodeOutClient);
						}
					}
					nodes.children.push(nodeOut);
				}
			}

			var companyData = this.getModel("companyModel").getData();
			companyData.companies = nodes;
			this.getModel("companyModel").refresh();
		},

		createCompanyDialog: function () {
			if (!this.pressDialog) {
				this.pressDialog = new Dialog({
					title: "Sirket adi giriniz",
					content: new SimpleForm({
						content: [
							new Input({ value: "{companyModel>/companyNameToCreated}" }),
						]
					}),
					beginButton: new Button({
						type: ButtonType.Emphasized,
						text: "Olustur",
						press: function () {
							this.createCompany();
							this.pressDialog.close();
						}.bind(this)
					}),
					endButton: new Button({
						text: "Iptal",
						press: function () {
							this.pressDialog.close();
						}.bind(this)
					})
				});
				
				//to get access to the global model
				this.getView().addDependent(this.pressDialog);
			}

			this.pressDialog.open();
		},

		createCompany: function () {
			this.showBusyIndicator();
			var companyNameToCreated = this.getModel("companyModel").getData().companyNameToCreated;
			MaindataService.createCompany({companyName : companyNameToCreated}).then(function (response) {
				var responseData = response.data;
				console.log(responseData);
				this.getCompanies();
				//hideBusy companyler geldikten sonra calisacak
			}.bind(this)).catch(function() {
				this.hideBusyIndicator();
			}.bind(this));
			console.log(companyNameToCreated);
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