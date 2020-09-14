sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/maindataservice",
    "sap/ui/model/json/JSONModel",
    "sap/m/Button",
    "sap/m/Dialog",
    "sap/m/Label",
    "sap/m/Input",
    "sap/ui/layout/form/SimpleForm",
    "sap/m/library",
    'workerapp/components/factory/components/maindata/model/formatter',
], function (BaseController, MaindataService, JSONModel, Button, Dialog, Label, Input, SimpleForm, mobileLibrary, formatter) {
    "use strict";

    var ButtonType = mobileLibrary.ButtonType;

    return BaseController.extend("workerapp.components.factory.components.maindata.controller.AssetDetail", {

        formatter: formatter,

        onInit: function () {


            this.oRouter = this.getRouter();
            this.oRouter.getRoute("assetDetail").attachPatternMatched(this._onMatched, this);


        },
        handleClose: function () {
            var sNextLayout = 'OneColumn';
            this.oRouter.navTo("asset", { layout: sNextLayout });
        },
        _onMatched: function (oEvent) {
            this.assetId = oEvent.getParameter("arguments").assetId;
            this.assetType = oEvent.getParameter("arguments").assetType;
            this.getModel("maindataModel").getData().assetType = this.assetType;
            this.getModel("maindataModel").refresh();
            this.getAssetDetail();
            // this.checkView(this.assetType);

        },

        getAssetDetail: function () {
            this.getAssetDetailInfo();
            if (this.assetType == "company") {

            } else if (this.assetType == "client") {

            } else if (this.assetType == "plant") {
                this.getWarehousesByPlantId();
                this.getMaterialsByPlantId();
                this.getQualityTypesByPlantId();
                this.getReasonCodesByPlantId();
            }
        },

        getAssetDetailInfo: function () {
            this.showBusyIndicator();
            var getAssetInfoService;
            if (this.assetType == "company") {
                getAssetInfoService = MaindataService.getCompanyInfo;
            } else if (this.assetType == "client") {
                getAssetInfoService = MaindataService.getClientInfo;
            } else if (this.assetType == "plant") {
                getAssetInfoService = MaindataService.getPlantInfo;
            }
            getAssetInfoService(this.assetId).then(function (response) {
                var responseData = response.data;
                this.getModel("maindataModel").getData().assetInfo = responseData;
                this.getModel("maindataModel").getData().assetInfo.assetName = this.formatter.assetDetailTitle(this.assetType, responseData);
                this.getModel("maindataModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));

        },


        getWarehousesByPlantId: function () {
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

        getMaterialsByPlantId: function () {
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

        getQualityTypesByPlantId: function () {
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

        getReasonCodesByPlantId: function () {
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
            } else if (this.action == "createQualityType") {
                isMaterial = true;
                title = oBundle.getText("ASSET_DIALOG_ENTRY_MATERIALNAME");
                labelMaterialDesc = oBundle.getText("ASSET_DIALOG_ENTRY_QUALITYDESC");
                labelMaterialNumber = oBundle.getText("ASSET_DIALOG_ENTRY_QUALITYTYPE");
            }
            else if (this.action == "createReasonCode") {
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
            this.getModel("maindataModel").getData().createdWarehouseName = "";
            this.getModel("maindataModel").getData().createdMaterialDesc = "";
            this.getModel("maindataModel").getData().createdMaterialNumber = "";
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
                    materialDesc: createdAsset.createdMaterialDesc,
                    materialNumber: createdAsset.createdMaterialNumber,
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
            } else if (this.action == "createQualityType") {
                createAssetFunction = MaindataService.createQualityType;
                dto = {
                    qualityDesc: createdAsset.createdMaterialDesc,
                    qualityType: createdAsset.createdMaterialNumber,
                    plant: {
                        plantId: this.assetId
                    }
                };
            } else if (this.action == "createReasonCode") {
                createAssetFunction = MaindataService.createReasonCode;
                dto = {
                    reasonDesc: createdAsset.createdMaterialDesc,
                    reasonCode: createdAsset.createdMaterialNumber,
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
        openDeleteAssetDialog(oEvent) {
            this._oDialog = this.deleteCompanyClientPlantDialog();
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();
        },
        deleteCompanyClientPlantDialog: function () {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();

            var title = "";
            var question = "";
            var deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
            var closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
            if (this.assetType == "company") {
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_QUESTION");
            } else if (this.assetType == "client") {
                title = oBundle.getText("ASSET_DIALOG_CLIENT_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_CLIENT_DELETE_QUESTION");
            } else if (this.assetType == "plant") {
                title = oBundle.getText("ASSET_DIALOG_PLANT_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_PLANT_DELETE_QUESTION");
            }


            return new Dialog({
                title: title,
                content: new SimpleForm({
                    content: [
                        new sap.m.Text({
                            wrapping: true,
                            wrappingType: sap.m.WrappingType.Hyphenated,
                            text: question
                        }),
                    ]
                }),
                beginButton: new Button({
                    type: ButtonType.Emphasized,
                    text: deleteBtnText,
                    press: function () {
                        if (this.assetType == "company") {
                            this.action = "deleteCompany";
                        } else if (this.assetType == "client") {
                            this.action = "deleteClient";
                        } else if (this.assetType == "plant") {
                            this.action = "deletePlant";
                        }
                        this.deleteAsset(this.assetId);
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


        openDeleteWarehouseDialog(oEvent) {
            this.action = "deleteWarehouse";
            var warehouseId;
            warehouseId = oEvent.getSource().getBindingContext("maindataModel").getObject().warehouseId;
            this._oDialog = this.deleteAssetDialog(warehouseId);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteMaterialDialog(oEvent) {
            this.action = "deleteMaterial";
            var materialID;
            materialID = oEvent.getSource().getBindingContext("maindataModel").getObject().materialId;
            this._oDialog = this.deleteAssetDialog(materialID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteReasonCodeDialog(oEvent) {
            this.action = "deleteReasonCode";
            var reasonID;
            reasonID = oEvent.getSource().getBindingContext("maindataModel").getObject().id;
            this._oDialog = this.deleteAssetDialog(reasonID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        openDeleteQualityTypeDialog(oEvent) {
            this.action = "deleteQualityType";
            var qualityID;
            qualityID = oEvent.getSource().getBindingContext("maindataModel").getObject().id;
            this._oDialog = this.deleteAssetDialog(qualityID);
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },
        deleteAssetDialog: function (assetDetailID) {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();
                var title = "";
                var question = "";
                var deleteBtnText;
                var closeBtnText;
            if (this.action == "deleteWarehouse") {
                deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
                closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_WAREHOUSE_DELETE_QUESTION");
            }
            else if (this.action == "deleteMaterial") {
                deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
                closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_MATERIAL_DELETE_QUESTION");
            }
            else if (this.action == "deleteReasonCode") {
                deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
                closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_REASONCODE_DELETE_QUESTION");
            }
            else if (this.action == "deleteQualityType") {
                deleteBtnText = oBundle.getText("ASSET_DIALOG_DELETE_BTN");
                closeBtnText = oBundle.getText("ASSET_DIALOG_CLOSE_BTN");
                title = oBundle.getText("ASSET_DIALOG_COMPANY_DELETE_TITLE");
                question = oBundle.getText("ASSET_DIALOG_QUALITYTYPE_DELETE_QUESTION");
            }

            return new Dialog({
                title: title,
                content: new SimpleForm({
                    content: [
                        new sap.m.Text({
                            wrapping: true,
                            wrappingType: sap.m.WrappingType.Hyphenated,
                            text: question
                        }),
                    ]
                }),
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

        deleteAsset: function (assetId) {
            this.showBusyIndicator();
            var deleteAssetFunction;
            var isCallAssetDetail = false;
            var isPlantClientCompany = false;
            if (this.action == "deleteCompany") {
                deleteAssetFunction = MaindataService.deleteCompanyById;
                isPlantClientCompany = true;
            }else if (this.action == "deleteClient") {
                deleteAssetFunction = MaindataService.deleteClientById;
                isPlantClientCompany = true;
            }else if (this.action == "deletePlant") {
                deleteAssetFunction = MaindataService.deletePlantById;
                isPlantClientCompany = true;
            }else if (this.action == "deleteWarehouse") {
                isCallAssetDetail = true;
                deleteAssetFunction = MaindataService.deleteWarehouseById;
            }else if (this.action == "deleteMaterial") {
                isCallAssetDetail = true;
                deleteAssetFunction = MaindataService.deleteMaterialById;
            }else if (this.action == "deleteReasonCode") {
                isCallAssetDetail = true;
                deleteAssetFunction = MaindataService.deleteReasonCodeById;
            }else if (this.action == "deleteQualityType") {
                isCallAssetDetail = true;
                deleteAssetFunction = MaindataService.deleteQualityTypeById;
            }
            deleteAssetFunction(assetId).then(function (response) {
                var responseData = response.data;
                if(isCallAssetDetail) {
                    this.getAssetDetail();
                }
                if(isPlantClientCompany) {
                    this.handleClose();
                }
                //hideBusy companyler geldikten sonra calisacak
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
                if(isPlantClientCompany) {
                    this.handleClose();
                }
            }.bind(this));
        },

        openUpdateWarehouseDialog: function(oEvent) {
            this.action = "updateWarehouse";
            var warehouse = oEvent.getSource().getBindingContext("maindataModel").getObject();
            this.updateAssetDialog(warehouse);
        },

        openUpdateMaterialDialog: function(oEvent) {
            this.action = "updateMaterial";
            var material = oEvent.getSource().getBindingContext("maindataModel").getObject();
            this.updateAssetDialog(material);
        },

        openUpdateQualityTypeDialog: function(oEvent) {
            this.action = "updateQualityType";
            var qualityType = oEvent.getSource().getBindingContext("maindataModel").getObject();
            this.updateAssetDialog(qualityType);
        },

        openUpdateReasonCodeDialog: function(oEvent) {
            this.action = "updateReasonCode";
            var reasonCode = oEvent.getSource().getBindingContext("maindataModel").getObject();
            this.updateAssetDialog(reasonCode);
        },

        updateAssetDialog: function(assetData) {
            var oBundle = this.getView().getModel("i18n").getResourceBundle();
            var form = new SimpleForm();
            var maindata = this.getView().getModel("maindataModel").getData();
            if (this.action == "updateWarehouse") {
                form.addContent(new sap.m.Label({text: oBundle.getText("WAREHOUSE_NAME")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedWarehouseName}"}));
                maindata.updatedWarehouseId = assetData.warehouseId;
                maindata.updatedWarehouseName = assetData.warehouseName;
            }
            else if (this.action == "updateMaterial") {
                form.addContent(new sap.m.Label({text: oBundle.getText("MATERIAL_NUMBER")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedMaterialNumber}"}));
                form.addContent(new sap.m.Label({text: oBundle.getText("MATERIAL_DESC")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedMaterialDesc}"}));
                maindata.updatedMaterialId = assetData.materialId;
                maindata.updatedMaterialNumber = assetData.materialNumber;
                maindata.updatedMaterialDesc = assetData.materialDesc;
            }
            else if (this.action == "updateQualityType") {
                form.addContent(new sap.m.Label({text: oBundle.getText("QUALITY_TYPE")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedQualityType}"}));
                form.addContent(new sap.m.Label({text: oBundle.getText("QUALITY_DESC")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedQualityDesc}"}));
                maindata.updatedQualityId = assetData.qualityId;
                maindata.updatedQualityType = assetData.qualityType;
                maindata.updatedQualityDesc = assetData.qualityDesc;
            }
            else if (this.action == "updateReasonCode") {
                form.addContent(new sap.m.Label({text: oBundle.getText("REASON_CODE")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedReasonCode}"}));
                form.addContent(new sap.m.Label({text: oBundle.getText("REASON_DESC")}));
                form.addContent(new sap.m.Input({value: "{maindataModel>/updatedReasonDesc}"}));
                maindata.updatedReasonId = assetData.reasonId;
                maindata.updatedReasonCode = assetData.reasonCode;
                maindata.updatedReasonDesc = assetData.reasonDesc;
            }
            this.getView().getModel("maindataModel").refresh();

            this._oDialog = new Dialog({
                title: oBundle.getText("UPDATE"),
                content: form,
                beginButton: new Button({
                    type: ButtonType.Emphasized,
                    text: oBundle.getText("UPDATE"),
                    press: function () {

                        this.updateAsset();
                    }.bind(this)
                }),
                endButton: new Button({
                    text: oBundle.getText("CANCEL"),
                    press: function () {
                        this.closeAssetDialog();
                    }.bind(this)
                })
            });
            this.getView().addDependent(this._oDialog);
            this._oDialog.open();

        },

        updateAsset: function() {
            this._oDialog.close();
            this.showBusyIndicator();
            var maindata = this.getView().getModel("maindataModel").getData();
            var updateAssetFunction;
            var updatedAsset;
            if (this.action == "updateWarehouse") {
                var warehouse = {
                    warehouseId: maindata.updatedWarehouseId,
                    warehouseName: maindata.updatedWarehouseName
                }
                updatedAsset = warehouse;
                updateAssetFunction = MaindataService.updateWarehouse;
            }
            else if (this.action == "updateMaterial") {
                var material = {
                    materialId: maindata.updatedMaterialId,
                    materialDesc: maindata.updatedMaterialDesc,
                    materialNumber: maindata.updatedMaterialNumber
                }
                updatedAsset = material;
                updateAssetFunction = MaindataService.updateMaterial;
            }
            else if (this.action == "updateQualityType") {
                var qualityType = {
                    qualityId: maindata.updatedQualityId,
                    qualityDesc: maindata.updatedQualityDesc,
                    qualityType: maindata.updatedQualityType
                }
                updatedAsset = qualityType;
                updateAssetFunction = MaindataService.updateQualityType;
            }
            else if (this.action == "updateReasonCode") {
                var reasonCode = {
                    reasonId: maindata.updatedReasonId,
                    reasonCode: maindata.updatedReasonCode,
                    reasonDesc: maindata.updatedReasonDesc
                }
                updatedAsset = reasonCode;
                updateAssetFunction = MaindataService.updateReasonCode;
            }
            
            updateAssetFunction(updatedAsset).then(function (response) {
                if (this.action == "updateWarehouse") {
                    this.getWarehousesByPlantId();
                } else if (this.action == "updateMaterial") {
                    this.getMaterialsByPlantId();
                } else if (this.action == "updateQualityType") {
                    this.getQualityTypesByPlantId();
                } else if (this.action == "updateReasonCode") {
                    this.getReasonCodesByPlantId();
                }
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

    });
}, true);
