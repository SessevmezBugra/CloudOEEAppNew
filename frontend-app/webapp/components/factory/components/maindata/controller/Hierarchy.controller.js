sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/maindataservice",
    "sap/ui/model/json/JSONModel",
    "sap/m/library",
    "sap/ui/core/Fragment",
    "workerapp/model/popup",
], function (BaseController, MaindataService, JSONModel, mobileLibrary, Fragment, Popup) {
    "use strict";

    var ButtonType = mobileLibrary.ButtonType;

    return BaseController.extend("workerapp.components.factory.components.maindata.controller.Hierarchy", {
        onInit: function () {
            var hierarchyModel = new JSONModel({
                hierarchyType: 0,
                newHierarchy: {
                    name: "",
                    child: [
                        {
                            name: "", hasParent: false, hasChild: false,
                            type: "GROUPING", 
                            availableNodeTypes: [
                                { key: "PLANT", desc: "Fabrika" },
                                { key: "GROUPING", desc: "Grup" }
                            ]

                        }
                    ]
                }
            });
            this.setModel(hierarchyModel, "hierarchyModel");
            this.getRouter().getRoute("hierarchy").attachPatternMatched(this._onMaterialMatched, this);
        },

        _onMaterialMatched: function (oEvent) {
            this.showBusyIndicator();
            this.getHierarchyHeaders();
        },

        getHierarchyHeaders: function () {
            MaindataService.getHierarchyHeaders().then(function (response) {
                var responseData = response.data;
                this.transformDataForTree(responseData);
                this.getModel("hierarchyModel").setProperty("/hierarchies", responseData);
                this.getModel("hierarchyModel").refresh();
                // this.transformTreeData(responseData);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        transformDataForTree: function (data) {
            for(var header of data) {
                var copy = Object.assign({}, header.child);
                header.child = [];
                header.child[0] = copy;
            }
        },

        openCreateHierarchyDialog: function () {
            var oView = this.getView();

            if (!this._pDialog) {
                this._pDialog = Fragment.load({
                    id: oView.getId(),
                    name: "workerapp.components.factory.components.maindata.fragment.createHierarchyDialog",
                    controller: this
                }).then(function (oDialog) {
                    oDialog.setModel(oView.getModel("hierarchyModel"), "hierarchyModel");
                    return oDialog;
                });
            }

            this._pDialog.then(function (oDialog) {
                oDialog.open();
            }.bind(this));
        },

        closeHierarchyDialog: function () {
            this._pDialog.then(function (oDialog) {
                oDialog.close();
            }.bind(this));
        },

        addHierarchy: function (oEvent) {
            var hierarchyType = oEvent.getSource().getModel("hierarchyModel").getData().hierarchyType;
            var hierarchy = oEvent.getSource().getBindingContext("hierarchyModel").getObject();
            if(hierarchy.type == "GROUPING" && hierarchyType == 0) {
                hierarchy.child = {
                    name: "", hasParent: true, hasChild: false, availableNodeTypes: [
                        { key: "GROUPING", desc: "Grup" },
                        { key: "PLANT", desc: "Fabrika" }
                    ]
                }
                hierarchy.hasChild = true;
            }else if(hierarchy.type == "PLANT" && hierarchyType == 0) {
                return;
            }else if(hierarchy.type == "PLANT" && hierarchyType == 1) {
                hierarchy.child = {
                    name: "", hasParent: true, hasChild: false, availableNodeTypes: [
                        { key: "GROUPING", desc: "Grup" },
                        { key: "WORK_CTR", desc: "Is Merkezi" }
                    ]
                }
                hierarchy.hasChild = true;
            }else if(hierarchy.type == "GROUPING" && hierarchyType == 1) {
                hierarchy.child = {
                    name: "", hasParent: true, hasChild: false, availableNodeTypes: [
                        { key: "GROUPING", desc: "Grup" },
                        { key: "WORK_CTR", desc: "Is Merkezi" }
                    ]
                }
                hierarchy.hasChild = true;
            }else if(hierarchy.type == "WORK_CTR" && hierarchyType == 1) {
                hierarchy.child = {
                    name: "", hasParent: true, hasChild: false, availableNodeTypes: [
                        { key: "MACHINE", desc: "Makine" }
                    ]
                }
                hierarchy.hasChild = true;
            }else if(hierarchy.type == "MACHINE" && hierarchyType == 1) {
                return;
            }
            
            oEvent.getSource().getModel("hierarchyModel").refresh();
            this.getView().byId("newHierarchyTree").expandToLevel(10);
        },

        removeHierarchy: function (oEvent) {
            var sPath = oEvent.getSource().getBindingContext("hierarchyModel").getPath();
            var endPath = sPath.lastIndexOf("/child");
            var parentSPath = sPath.substr(0, endPath);
            var parentHierarchy = oEvent.getSource().getModel("hierarchyModel").getProperty(parentSPath);
            parentHierarchy.hasChild = false;
            oEvent.getSource().getModel("hierarchyModel").setProperty(sPath, null);
            oEvent.getSource().getModel("hierarchyModel").refresh();
            this.getView().byId("newHierarchyTree").expandToLevel(10);
        },

        onChangeHierarchyType: function (oEvent) {

            var selectedType = oEvent.getParameter("selectedIndex");
            if (selectedType == 0) {
                oEvent.getSource().getModel("hierarchyModel").getData().newHierarchy = {
                    name: "",
                    child: [
                        {
                            name: "", hasParent: false, hasChild: false, type: "GROUPING",
                            availableNodeTypes: [
                                { key: "PLANT", desc: "Fabrika" },
                                { key: "GROUPING", desc: "Grup" }
                            ]
    
                        }
                    ]
                };
            } else {
                oEvent.getSource().getModel("hierarchyModel").getData().newHierarchy = {
                    name: "",
                    child: [
                        {
                            name: "", hasParent: false, hasChild: false, type: "PLANT",
                            availableNodeTypes: [
                                { key: "PLANT", desc: "Fabrika" },
                                { key: "GROUPING", desc: "Grup" },
                                { key: "WORK_CTR", desc: "Is Merkezi" }
                            ]
    
                        }
                    ]
                };
            }
            oEvent.getSource().getModel("hierarchyModel").refresh();
        },

        createHierarchy: function(oEvent) {
            var data = oEvent.getSource().getModel("hierarchyModel").getData();
            var newHierarchyData = {
                name: data.newHierarchy.name,
                type: data.hierarchyType == 0 ? "GLOBAL" : "PLANT",
                child: data.newHierarchy.child[0]
            }
            var isValidate = data.hierarchyType == 0 ? this.validateNewGlobalHierarchy(newHierarchyData) : this.validateNewPlantHierarchy(newHierarchyData);
            var message = data.hierarchyType == 0 ? "Hiyerarsi bir fabrika ile son bulmalidir" : "Hiyerarsi bir calisma merkezi veya makine ile son bulmalidir";
            if(!isValidate) {
                Popup.messageBox("warning", message, function (isOk) {
                    if (isOk) {
                        this.updateUserRole(this.group);
                    }
                }.bind(this));
                return;
            }

            MaindataService.createHierarchy(newHierarchyData).then(function (response) {
                this.getHierarchyHeaders();
                this.closeHierarchyDialog();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
        },

        validateNewGlobalHierarchy: function (data) {
            var isValidate = false;
            if(data.child){
                isValidate = this.validateNewGlobalHierarchy(data.child);
            }else if(data.type == "PLANT"){
                isValidate = true;
            }
            return isValidate;
        },

        validateNewPlantHierarchy: function (data) {
            var isValidate = false;
            if(data.child){
                isValidate = this.validateNewPlantHierarchy(data.child);
            }else if(data.type == "WORK_CTR" || data.type == "MACHINE"){
                isValidate = true;
            }
            return isValidate;
        }

    });
});