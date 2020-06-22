sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/companyservice",
	"sap/ui/model/json/JSONModel"
], function (BaseController, CompanyService, JSONModel) {
	"use strict";
	var response;
	return BaseController.extend("workerapp.components.factory.components.maindata.controller.Client", {
		onInit: function () {
			this.getRouter().getRoute("client").attachPatternMatched(this._onClientMatched, this);
		},
		_onClientMatched: function (oEvent) {
			var id = oEvent.getParameter("arguments").id;
			var self = this;
			CompanyService.getClientInfo(parseInt(id)).then(function (response) {
				response = response.data;
				self.transformTreeData(response);
			}); 
		},
		transformTreeData: function (jsonData) {
			var nodes = {
				id: jsonData.clientId,
				text: jsonData.clientName,
				children: []
			};
			var plants = jsonData.plants;
			if (plants) {
				var nodeOut;
				var warehouses;
				var nodeOutWarehouse;
				for (var i = 0; i < plants.length; i++) {
					var plant = plants[i];
					nodeOut = {
						id: plant.plantId,
						text: plant.plantName,
						type: "plant",
						children: []
					};
					warehouses = plant.warehouses;
					if (warehouses && warehouses.length > 0) {
						for (var j = 0; j < warehouses.length; j++) {
							var warehouse = warehouses[j];
							nodeOutWarehouse = {
								id: warehouse.warehouseId,
								text: warehouse.warehouseName,
								type: "warehouse",
								children: []
							};
							
							nodeOut.children.push(nodeOutWarehouse);
						}

					} else {
						nodes.push(nodeOut);
					}
					nodes.children.push(nodeOut);
				}
			}
			var oModel = new JSONModel();
			oModel.setData({
				clientInfo: nodes
			});
			this.getView().setModel(oModel);
		},
		onSelectedItem: function (oEvent) {
			var item = oEvent.getSource().getSelectedItem().getBindingContext().getObject();
			var id = item.id;
			var type = item.type;
			if (type === "plant") {
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