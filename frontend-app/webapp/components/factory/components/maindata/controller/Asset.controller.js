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

    return BaseController.extend("workerapp.components.factory.components.maindata.controller.Asset", {
        onInit: function () {
            var assetModel = new JSONModel({
                createdAssetName: "",
                parentAssetId: 0
            });
            this.setModel(assetModel, "assetModel");
            this.getRouter().getRoute("asset").attachPatternMatched(this._onMaterialMatched, this);

        },

        _onMaterialMatched: function (oEvent) {
            this.showBusyIndicator(); //hide companyler gelince calisacak.
            this.getAssets();
        },

        getAssets: function () {
            MaindataService.getCompanies().then(function (response) {
                var responseData = response.data;
                this.transformTreeData(responseData);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
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
                                    // warehouses = plant.warehouses;
                                    // for (var k = 0; k < warehouses.length; k++) {
                                    //     var warehouse = warehouses[k];
                                    //     nodeOutWarehouse = {
                                    //         id: warehouse.warehouseId,
                                    //         text: warehouse.warehouseName,
                                    //         type: "warehouse",
                                    //         children: []
                                    //     };
                                    //     nodeOutPlant.children.push(nodeOutWarehouse);
                                    // }
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

            var assetData = this.getModel("assetModel").getData();
            assetData.assets = nodes;
            this.getModel("assetModel").refresh();
        },

        openCreateAssetDialog: function (oEvent) {
            var oEvent = oEvent;
            var context = oEvent.getSource().getParent().getBindingContext("assetModel");
            var type;
            this.parentAssetId = 0;
            if (context) {
                type = context.getObject().type;
                this.parentAssetId = context.getObject().id;
            } else {
                this.action = "createCompany";
            }

            if (type == "company") {
                this.action = "createClient";
            } else if (type == "client") {
                this.action = "createPlant";
            }

            this._oDialog = this.createAssetDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },

        closeAssetDialog: function () {
            this._oDialog.close();
            this.clearDialog();
        },

        clearDialog: function() {
            this.getModel("assetModel").getData().createdAssetName = "";
            this.getModel("assetModel").refresh();
        },

        createAsset: function () {
            this.showBusyIndicator();
            var createdAssetName = this.getModel("assetModel").getData().createdAssetName;
            var createAssetFunction;
            var dto;
            if (this.action == "createCompany") {
                createAssetFunction = MaindataService.createCompany;
                dto = { companyName: createdAssetName };
            } else if (this.action == "createClient") {
                createAssetFunction = MaindataService.createClient;
                dto = {
                    clientName: createdAssetName,
                    company: {
                        companyId: this.parentAssetId
                    }
                };
            } else if (this.action == "createPlant") {
                createAssetFunction = MaindataService.createPlant;
                dto = {
                    plantName: createdAssetName,
                    client: {
                        clientId: this.parentAssetId
                    }
                };
            }
            createAssetFunction(dto).then(function (response) {
                var responseData = response.data;
                console.log(responseData);
                this.getAssets();
                //hideBusy companyler geldikten sonra calisacak
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
            console.log(createdAssetName);
        },

        createAssetDialog: function () {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();

            var title = "";
            var createBtnText = oBundle.getText("ASSET_DIALOG_CREATE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");;
            if (this.action == "createCompany") {
                title = oBundle.getText("ASSET_DIALOG_ENTRY_COMPANYNAME");
            } else if (this.action == "createClient") {
                title = oBundle.getText("ASSET_DIALOG_ENTRY_CLIENTNAME");
            } else if (this.action == "createPlant") {
                title = oBundle.getText("ASSET_DIALOG_ENTRY_PLANTNAME");
            }

            return new Dialog({
                title: title,
                content: new SimpleForm({
                    content: [
                        new Input({ value: "{assetModel>/createdAssetName}" }),
                    ]
                }),
                beginButton: new Button({
                    type: ButtonType.Emphasized,
                    text: createBtnText,
                    press: function () {
                        this.createAsset();
                        this.closeAssetDialog();
                    }.bind(this)
                }),
                endButton: new Button({
                    text: closeBtnText,
                    press: function () {
                        this.closeAssetDialog();
                    }.bind(this)
                })
            });

        },

        // Tree Table a tiklanildiginda calisir
        // goToAssetDetail: function(oEvent) {
        //     var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
        // 	var asset = oEvent.getParameter("listItem").getBindingContext("assetModel").getObject();

        // 	this.getRouter().navTo("assetDetail", {assetId: asset.id, assetType: asset.type, layout: oNextUIState.layout});
        // }

        goToAssetDetail: function (oEvent) {
            var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
            var asset = oEvent.getSource().getBindingContext("assetModel").getObject();
            this.getRouter().navTo("assetDetail", { assetId: asset.id, assetType: asset.type, layout: oNextUIState.layout });
        }

    });
});