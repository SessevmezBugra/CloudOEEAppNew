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
                this.getQualityTypeByPlantId();
                this.getReasonCodeByPlantId();
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

        getQualityTypeByPlantId: function() {
            this.getModel("maindataModel").getData().qualityTypeTableBusy = true;
            MaindataService.getQualityTypesByPlantId(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().qualityType = responseData;
                this.getModel("maindataModel").getData().qualityTypeTableBusy = false;
                this.getModel("maindataModel").refresh();
                // this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                // this.hideBusyIndicator();
            }.bind(this));
        },

        getReasonCodeByPlantId: function() {
            this.getModel("maindataModel").getData().reasonCodeTableBusy = true;
            MaindataService.getReasonCodesByPlantId(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().reasonCode = responseData;
                this.getModel("maindataModel").getData().reasonCodeTableBusy = false;
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
        openCreateReasonDialog: function (oEvent) {
            this.action = "createReasonCode";
            this._oDialog = this.createAssetDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },
        openCreateQualityTypeDialog: function (oEvent) {
            this.action = "createQualityType";
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
            }else if(this.action =="createQualityType")
            {
                isMaterial = true;
                title = oBundle.getText("ASSET_DIALOG_ENTRY_MATERIALNAME");
                labelMaterialDesc = oBundle.getText("ASSET_DIALOG_ENTRY_QUALITYDESC");
                labelMaterialNumber = oBundle.getText("ASSET_DIALOG_ENTRY_QUALITYTYPE");
            }
            else if(this.action =="createReasonCode")
            {
                isMaterial = true;
                title = oBundle.getText("ASSET_DIALOG_ENTRY_MATERIALNAME");
                labelMaterialDesc = oBundle.getText("ASSET_DIALOG_ENTRY_REASONDESC");
                labelMaterialNumber = oBundle.getText("ASSET_DIALOG_ENTRY_REASONCODE");
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
            }else if (this.action =="createQualityType")
            {
                createAssetFunction = MaindataService.createQualityType;
                dto = { 
                    qualityDesc :  createdAsset.createdMaterialDesc,
                    qualityType: createdAsset.createdMaterialNumber,
                    plant: {
                        plantId: this.assetId
                    } 
                };
            }else if (this.action =="createReasonCode")
            {
                createAssetFunction = MaindataService.createReasonCode;
                dto = { 
                    reasonDesc :  createdAsset.createdMaterialDesc,
                    reasonCode : createdAsset.createdMaterialNumber,
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

        openDeleteWarehouseDialog (oEvent)
        {
            this.action= "deleteWarehouse";
            var warehouseId;
            warehouseId = oEvent.getSource().getBindingContext("maindataModel").getObject().warehouseId;
            this._oDialog = this.deleteAssetDialog(warehouseId);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteMaterialDialog (oEvent)
        {
            this.action= "deleteMaterial";
            var materialID;
            materialID = oEvent.getSource().getBindingContext("maindataModel").getObject().materialId;
            this._oDialog = this.deleteAssetDialog(materialID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteReasonCodeDialog (oEvent)
        {
            this.action= "deleteReasonCode";
            var reasonID;
            reasonID = oEvent.getSource().getBindingContext("maindataModel").getObject().id;
            this._oDialog = this.deleteAssetDialog(reasonID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteQualityTypeDialog (oEvent)
        {
            this.action= "deleteQualityType";
            var qualityID;
            qualityID = oEvent.getSource().getBindingContext("maindataModel").getObject().id;
            this._oDialog = this.deleteAssetDialog(qualityID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        deleteAssetDialog: function (assetDetailID) {
            if(this.action =="deleteWarehouse")
            {
                var oBundle = this.getView().getModel("i18n").getResourceBundle();
                var assetDetailId = assetDetailID;
                var title = "";
                var question = "";
                var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
                var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_WAREHOUSE_DELETE_QUESTION");
            }
           else if(this.action == "deleteMaterial")
           {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();
            var assetDetailId = assetDetailID;
            var title = "";
            var question = "";
            var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
            question = oBundle.getText("ASSET_DIALOG_MATERIAL_DELETE_QUESTION");
           }
           else if(this.action == "deleteReasonCode")
           {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();
            var assetDetailId = assetDetailID;
            var title = "";
            var question = "";
            var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
            question = oBundle.getText("ASSET_DIALOG_REASONCODE_DELETE_QUESTION");
           }
           else if(this.action == "deleteQualityType")
           {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();
            var assetDetailId = assetDetailID;
            var title = "";
            var question = "";
            var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
            question = oBundle.getText("ASSET_DIALOG_QUALITYTYPE_DELETE_QUESTION");
           }
            
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
                       this.deleteAsset(assetDetailId);
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

        deleteAsset: function (assetDetailID) {
            this.showBusyIndicator();
            var deleteAssetFunction;
            if(this.action =="deleteWarehouse")
            deleteAssetFunction = MaindataService.deleteWarehouse;  
            else if(this.action == "deleteMaterial")
            deleteAssetFunction = MaindataService.deleteMaterial;  
            else if(this.action == "deleteReasonCode")
            deleteAssetFunction = MaindataService.deleteReasonCode;  
            else if(this.action == "deleteQualityType")
            deleteAssetFunction = MaindataService.deleteQualityType;  
            deleteAssetFunction(assetDetailID).then(function (response) {
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
