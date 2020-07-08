sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter',
	'sap/ui/core/SeparatorItem',
	'sap/ui/core/Fragment',
	'sap/ui/model/Filter',
	'sap/m/Token',
	'sap/ui/model/FilterOperator',
	"workerapp/services/maindataservice"
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, Fragment, Filter, Token, FilterOperator, MaindataService) {
	"use strict";
	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffTracking", {
		formatter: formatter,

		onInit: function () {
			this.oRouter = this.getOwnerComponent().getRouter();
            this.getRouter().getRoute("staffTracking").attachPatternMatched(this._onMaterialMatched, this);
		},
		_onMaterialMatched: function (oEvent) {
			
            // this.showBusyIndicator(); //hide companyler gelince calisacak.
		},

		openCreatePersonDialog: function () {
			//this.showBusyIndicator(); //show fabrikalar gelince calisacak.
            this.getPlants(function (isOk) {
                if (!this._oDialog) {
                    this._oDialog = sap.ui.xmlfragment("createPersonDialog","workerapp.components.factory.components.staffmanagement.fragment.createPersonDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
				this._oDialog.open();
				// this._oDialog.byId("DTP2").setMinDate(new Date());
				var date = new Date();
				sap.ui.core.Fragment.byId("createPersonDialog", "DTP2").setMinDate(new Date(date.getFullYear(), date.getMonth(), date.getDate()));
				var oMultiInput = sap.ui.core.Fragment.byId("createPersonDialog", "stockMultiInput");
				oMultiInput.addValidator(function(args){
					var key = args.suggestionObject.getKey();
					var tokens = oMultiInput.getTokens();
					var isValid = true;
					for(var token of tokens) {
						if (token.getKey() == key){
							isValid = false;
						}
					}
					if(isValid){
						return new Token({key: key, text: args.text});
					}
				});
            }.bind(this));
		},
		getPlants: function (callback) {
            MaindataService.getPlants().then(function (response) {
                var responseData = response.data;
                this.getModel("staffModel").getData().plants = responseData;
                this.getModel("staffModel").refresh();
                callback(true);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
		},

		closePersonDialog: function () {
			this._oDialog.close();
			this.clearPersonDialog();
		},
		
		clearPersonDialog: function() {
			var personDialogData = this.getModel("staffModel").getData();
			personDialogData.materialNumberToProduced = "";
			personDialogData.materialDescToProduced= "";
			personDialogData.customerName= "";
			personDialogData.orderNumber= "";
			this.getModel("staffModel").refresh();
			var oMultiInput = sap.ui.core.Fragment.byId("createPersonDialog", "stockMultiInput");
			if(oMultiInput){
				oMultiInput.destroyTokens();
			}
		},
		createPerson: function () {
			var personData = this.getModel("staffModel").getData();

			var personadd = {
				plantId: personData.selectedPlant,
				orderNo: personData.orderNumber,
				customer: personData.customerName,
				plannedStartDate: personData.plannedStartDate,
				plannedEndDate: personData.plannedEndDate,
				orderedMaterial: {
					materialDesc: personData.materialDescToProduced,
					materialNumber: personData.materialNumberToProduced,
					plannedProdQuantity: personData.materialQuantityToProduced
				},
				consumptionMaterials: [
					
				]

			};
			var oMultiInput = sap.ui.core.Fragment.byId("createPersonDialog", "stockMultiInput");
			for(var token of oMultiInput.getTokens()) {
				personadd.consumptionMaterials.push({
					stockId: token.getKey()
				});
			}
			OrderService.createOrder(personadd).then(function (response) {
				this.closePersonDialog();
                // callback(true);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                // callback(false);
                this.hideBusyIndicator();
            }.bind(this));

		},

		onListItemPress: function (oEvent) {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
				// ,
				// productPath = oEvent.getSource().getBindingContext("orderModel").getPath(),
				// product = productPath.split("/").slice(-1).pop();

			this.oRouter.navTo("staffDetail", {layout: oNextUIState.layout, userId: 1});

			var oItem = oEvent.getSource();
			oItem.setNavigated(true);
			var oParent = oItem.getParent();
			// store index of the item clicked, which can be used later in the columnResize event
			this.iIndex = oParent.indexOfItem(oItem);
		},

		onSearch: function (oEvent) {
			var oTableSearchState = [],
				sQuery = oEvent.getParameter("query");

			if (sQuery && sQuery.length > 0) {
				oTableSearchState = [new Filter("materialDesc", FilterOperator.Contains, sQuery)];
			}

			this.getView().byId("staffTable").getBinding("items").filter(oTableSearchState, "Application");
		},

        toPage2: function () {
            var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
            // ,
            // productPath = oEvent.getSource().getBindingContext("orderModel").getPath(),
            // product = productPath.split("/").slice(-1).pop();

             this.oRouter.navTo("StaffDetail", {layout: oNextUIState.layout});
        }
	});
});