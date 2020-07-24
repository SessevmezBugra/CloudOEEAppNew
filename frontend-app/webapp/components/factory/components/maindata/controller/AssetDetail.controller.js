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

	return BaseController.extend("workerapp.components.factory.components.maindata.controller.AssetDetail", {
		onInit: function () {


			this.oRouter = this.getRouter();
            this.oRouter.getRoute("assetDetail").attachPatternMatched(this._onMatched, this);

            
		},
		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("asset", {layout: sNextLayout});
		},
		_onMatched: function (oEvent) {
            this.assetId = oEvent.getParameter("arguments").assetId;
            this.assetType = oEvent.getParameter("arguments").assetType;
            this.getAssetDetail();
            this.checkView(this.assetType);

		},

        getAssetDetail: function() {
            this.getAssetDetailInfo();
            if(this.assetType == "company") {
                
            }else if(this.assetType == "client") {

            }else if(this.assetType == "plant") {
                this.getWarehousesByPlantId();
                this.getMaterialsByPlantId();
            }
        },
        checkView(assetType)
        {
            if(assetType== "company")
            {
                this.getView().byId("ObjectPageLayoutCompany").setVisible(true);
                this.getView().byId("ObjectPageLayoutClient").setVisible(false);
                this.getView().byId("ObjectPageLayoutPlant").setVisible(false);

            }
            if(assetType == "client"){
               this.getView().byId("ObjectPageLayoutClient").setVisible(true);
               this.getView().byId("ObjectPageLayoutCompany").setVisible(false);
               this.getView().byId("ObjectPageLayoutPlant").setVisible(false);
            }
            if(assetType =="plant"){
                this.getView().byId("ObjectPageLayoutPlant").setVisible(true);
                this.getView().byId("ObjectPageLayoutClient").setVisible(false);
                this.getView().byId("ObjectPageLayoutCompany").setVisible(false);

             }


        },
        getAssetDetailInfo: function () {
            this.showBusyIndicator();
            var getAssetInfoService;
            if(this.assetType == "company") {
                getAssetInfoService = MaindataService.getCompanyInfo;
            }else if(this.assetType == "client") {
                getAssetInfoService = MaindataService.getClientInfo;
            }else if(this.assetType == "plant") {
                getAssetInfoService = MaindataService.getPlantInfo;
            }
            getAssetInfoService(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().assetInfo = responseData;
                this.getModel("maindataModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));

        },
        

        getWarehousesByPlantId: function() {
            this.getModel("maindataModel").getData().warehouseTableBusy = true;
            MaindataService.getWarehousesByPlantId(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().warehouses = responseData;
                this.getModel("maindataModel").getData().warehouseTableBusy = false;
                this.getModel("maindataModel").refresh();
                // this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                // this.hideBusyIndicator();
            }.bind(this));
        },

        getMaterialsByPlantId: function() {
            this.getModel("maindataModel").getData().materialTableBusy = true;
            MaindataService.getMaterialsByPlantId(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().materials = responseData;
                this.getModel("maindataModel").getData().materialTableBusy = false;
                this.getModel("maindataModel").refresh();
                // this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                // this.hideBusyIndicator();
            }.bind(this));
        },

        openCreateWarehouseDialog: function (oEvent) {
            this.action = "createWarehouse";
            this._oDialog = this.createAssetDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },

        openCreateMaterialDialog: function (oEvent) {
            this.action = "createMaterial";
            this._oDialog = this.createAssetDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },

        closeAssetDialog: function () {
            this._oDialog.close();
            this.clearDialog();
        },

        createAssetDialog: function () {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();

            var title = "";
            var labelMaterialDesc = "";
            var labelMaterialNumber = "";
            var createBtnText = oBundle.getText("ASSET_DIALOG_CREATE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            var isMaterial = false;
            if (this.action == "createWarehouse") {
                title = oBundle.getText("ASSET_DIALOG_ENTRY_WAREHOUSENAME");
            } else if (this.action == "createMaterial") {
                isMaterial = true;
                title = oBundle.getText("ASSET_DIALOG_ENTRY_MATERIALNAME");
                labelMaterialDesc = oBundle.getText("ASSET_DIALOG_LABEL_MATERIALDESC");
                labelMaterialNumber = oBundle.getText("ASSET_DIALOG_LABEL_MATERIALNUMBER");
            }

            return new Dialog({
                title: title,
                content: new SimpleForm({
                    content: [
                        new Input({ value: "{maindataModel>/createdWarehouseName}", visible: !isMaterial }),

                        new Label({ text: labelMaterialDesc, visible: isMaterial }),
                        new Input({ value: "{maindataModel>/createdMaterialDesc}", visible: isMaterial }),
                        new Label({ text: labelMaterialNumber, visible: isMaterial }),
                        new Input({ value: "{maindataModel>/createdMaterialNumber}", visible: isMaterial }),
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

        clearDialog: function () {
            this.getModel("maindataModel").getData().createdWarehouseName ="";
            this.getModel("maindataModel").getData().createdMaterialDesc ="";
            this.getModel("maindataModel").getData().createdMaterialNumber ="";
            this.getModel("maindataModel").refresh();
        },

        createAsset: function () {
            this.showBusyIndicator();
            var createdAsset = this.getModel("maindataModel").getData();
            var createAssetFunction;
            var dto;
            if (this.action == "createMaterial") {
                createAssetFunction = MaindataService.createMaterial;
                dto = { 
                    materialDesc :  createdAsset.createdMaterialDesc,
                    materialNumber : createdAsset.createdMaterialNumber,
                    plant: {
                        plantId: this.assetId
                    } 
                };
            } else if (this.action == "createWarehouse") {
                createAssetFunction = MaindataService.createWarehouse;
                dto = {
                    warehouseName: createdAsset.createdWarehouseName,
                    plant: {
                        plantId: this.assetId
                    }
                };
            }
            createAssetFunction(dto).then(function (response) {
                var responseData = response.data;
                console.log(responseData);
                this.getAssetDetail();
                //hideBusy companyler geldikten sonra calisacak
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },
        openDeleteCompanyDialog (oEvent)
        {
            this._oDialog = this.deleteCompanyDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },
        deleteCompanyDialog: function () {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();

            var title = "";
            var question = "";
            var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
            question = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_QUESTION");
            
            return new Dialog({
                title: title,
                content: new SimpleForm({
                    content: [
                        new sap.m.Text({ 
                            wrapping:true,
                            wrappingType : sap.m.WrappingType.Hyphenated,
                            text: question
                        }),
                    ]
                }) ,
                beginButton: new Button({
                    type: ButtonType.Emphasized,
                    text: deleteBtnText,
                    press: function () {
                        //servis gelince yorumdan çıkart
                       this.deleteCompany();
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


        deleteCompany: function () {
            this.showBusyIndicator();
            var deleteAssetFunction;
            var dto;
            deleteAssetFunction = MaindataService.deleteCompanyInfoById;          
            deleteAssetFunction(this.assetId).then(function (response) {
                var responseData = response.data;
                console.log(responseData +"<----");
                this.getAssetDetail();
                //hideBusy companyler geldikten sonra calisacak
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },
		
	});
}, true);
