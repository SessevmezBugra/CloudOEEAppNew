sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/maindataservice",
    "sap/ui/model/json/JSONModel"
], function (BaseController, MaindataService, JSONModel) {
    "use strict";

    return BaseController.extend("workerapp.components.factory.components.maindata.controller.Material", {
        onInit: function () {
            var materialModel = new JSONModel({
                materialNameToCreated: ""
            });
            this.setModel(materialModel, "materialModel");
            this.getRouter().getRoute("material").attachPatternMatched(this._onMaterialMatched, this);

        },

        _onMaterialMatched: function (oEvent) {
            this.showBusyIndicator(); //hide companyler gelince calisacak.
            this.getMaterials();
        },

        getMaterials: function () {
            MaindataService.getMaterials().then(function (response) {
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
                this.getModel("materialModel").getData().plants = responseData;
                this.getModel("materialModel").refresh();
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
                var materials;
                var nodeOutMaterial;
                materials = jsonData;
                for (var k = 0; k < materials.length; k++) {
                    var material = materials[k];
                    nodeOutMaterial = {
                        id: material.materialId,
                        text: material.materialDesc + " " + material.materialNumber,
                        type: "material",
                        children: []
                    };
                    nodes.children.push(nodeOutMaterial);
                }
            }

            var materialData = this.getModel("materialModel").getData();
            materialData.materials = nodes;
            this.getModel("materialModel").refresh();
        },

        openMaterialDialog: function () {
            this.showBusyIndicator();//show companyler gelince calisacak.
            this.getPlants(function (isOk) {
                if (!this._oDialog) {
                    this._oDialog = sap.ui.xmlfragment("workerapp.components.factory.components.maindata.fragment.createMaterialDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
                this._oDialog.open();
            }.bind(this));
        },

        closeMaterialDialog: function () {
            this._oDialog.close();
        },

        createMaterial: function () {
            this.showBusyIndicator();
            var materialDescToCreated = this.getModel("materialModel").getData().materialDescToCreated;
            var materialNumberToCreated = this.getModel("materialModel").getData().materialNumberToCreated;
            var selectedPlant = this.getModel("materialModel").getData().selectedPlant;
            var material = {
                materialDesc :  materialDescToCreated,
                materialNumber : materialNumberToCreated,
                plant: {
                    plantId: selectedPlant
                }
            };
            this.closeMaterialDialog();
            MaindataService.createMaterial(material).then(function (response) {
                var responseData = response.data;
                console.log(responseData);
                this.getMaterials();
                //hideBusy malzemeler geldikten sonra calisacak
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