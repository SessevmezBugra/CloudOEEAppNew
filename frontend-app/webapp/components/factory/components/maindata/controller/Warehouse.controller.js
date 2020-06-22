sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/maindataservice",
    "sap/ui/model/json/JSONModel"
], function (BaseController, MaindataService, JSONModel) {
    "use strict";

    return BaseController.extend("workerapp.components.factory.components.maindata.controller.Warehouse", {
        onInit: function () {
            var warehouseModel = new JSONModel({
                warehouseNameToCreated: ""
            });
            this.setModel(warehouseModel, "warehouseModel");
            this.getRouter().getRoute("warehouse").attachPatternMatched(this._onMaterialMatched, this);

        },

        _onMaterialMatched: function (oEvent) {
            this.showBusyIndicator(); //hide companyler gelince calisacak.
            this.getWarehouses();
        },

        getWarehouses: function () {
            MaindataService.getWarehouses().then(function (response) {
                var responseData = response.data;
                this.transformTreeData(responseData);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        getPlants: function (callback) {
            MaindataService.getPlants().then(function (response) {
                var responseData = response.data;
                this.getModel("warehouseModel").getData().plants = responseData;
                this.getModel("warehouseModel").refresh();
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
                var warehouses;
                var nodeOutWarehouse;
                warehouses = jsonData;
                for (var k = 0; k < warehouses.length; k++) {
                    var warehouse = warehouses[k];
                    nodeOutWarehouse = {
                        id: warehouse.warehouseId,
                        text: warehouse.warehouseName,
                        type: "warehouse",
                        children: []
                    };
                    nodes.children.push(nodeOutWarehouse);
                }
            }

            var warehouseData = this.getModel("warehouseModel").getData();
            warehouseData.warehouses = nodes;
            this.getModel("warehouseModel").refresh();
        },

        openWarehouseDialog: function () {
            this.showBusyIndicator();//show companyler gelince calisacak.
            this.getPlants(function (isOk) {
                if (!this._oDialog) {
                    this._oDialog = sap.ui.xmlfragment("workerapp.components.factory.components.maindata.fragment.createWarehouseDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
                this._oDialog.open();
            }.bind(this));
        },

        closeWarehouseDialog: function () {
            this._oDialog.close();
        },

        createWarehouse: function () {
            this.showBusyIndicator();
            var warehouseNameToCreated = this.getModel("warehouseModel").getData().warehouseNameToCreated;
            var selectedPlant = this.getModel("warehouseModel").getData().selectedPlant;
            var warehouse = {
                warehouseName: warehouseNameToCreated,
                plant: {
                    plantId: selectedPlant
                }
            };
            this.closeWarehouseDialog();
            MaindataService.createWarehouse(warehouse).then(function (response) {
                var responseData = response.data;
                console.log(responseData);
                this.getWarehouses();
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