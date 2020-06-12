sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/companyservice",
	"sap/ui/model/json/JSONModel"
], function (BaseController, CompanyService, JSONModel) {
	"use strict";
	var response;
	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Company", {
		onInit: function () {
			var self = this;
			CompanyService.getCompanyInfo().then(function (response) {
				response = response.data;
				self.transformTreeData(response);
			});
		},
		transformTreeData: function (jsonData) {
			var nodes = {
				id: jsonData.companyId,
				text: jsonData.companyName,
				children: []
			};
			var clients = jsonData.clients;
			if (clients) {
				var nodeOut;
				var plants;
				var warehouses;
				var nodeOutPlant;
				var nodeOutWarehouse;
				for (var i = 0; i < clients.length; i++) {
					var client = clients[i];
					nodeOut = {
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
							nodeOut.children.push(nodeOutPlant);
						}

					} else {
						nodes.push(nodeOut);
					}
					nodes.children.push(nodeOut);
				}
			}
			var oModel = new JSONModel();
			oModel.setData({
				companyInfo: nodes
			});
			this.getView().setModel(oModel);
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