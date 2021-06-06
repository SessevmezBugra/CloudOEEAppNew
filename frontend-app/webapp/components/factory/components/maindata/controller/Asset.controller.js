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

            });
            this.setModel(assetModel, "assetModel");
            this.getRouter().getRoute("asset").attachPatternMatched(this._onMaterialMatched, this);

        },

        _onMaterialMatched: function (oEvent) {
            this.getGlobalNodes();
        },

        getGlobalNodes: function () {
            this.showBusyIndicator();
            MaindataService.getGlobalNodes().then(function (response) {
                var responseData = response.data;
                this.transformTreeData(responseData);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        openCreateAssetDialog: function (oEvent) {
            
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
            
        },

        // Tree Table a tiklanildiginda calisir
        goToAssetDetailTreeTable: function(oEvent) {
            var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
       	var asset = oEvent.getParameter("listItem").getBindingContext("assetModel").getObject();

         	this.getRouter().navTo("assetDetail", {assetId: asset.id, assetType: asset.type, layout: oNextUIState.layout});
       },

        goToAssetDetail: function (oEvent) {
            var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
            var asset = oEvent.getSource().getBindingContext("assetModel").getObject();
            this.getRouter().navTo("assetDetail", { assetId: asset.id, assetType: asset.type, layout: oNextUIState.layout });
        }

    });
});