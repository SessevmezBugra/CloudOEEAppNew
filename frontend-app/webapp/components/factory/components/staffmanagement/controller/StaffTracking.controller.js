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
	"workerapp/services/maindataservice",
	"workerapp/services/authservice",
	"sap/m/MessageBox"
], function (BaseController, JSONModel, Device, formatter, SeparatorItem, Fragment, Filter, Token, FilterOperator, MaindataService, AuthService, MessageBox) {
	"use strict";

	var clicked = false;

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffTracking", {
		formatter: formatter,

		onInit: function () {
			this.oRouter = this.getOwnerComponent().getRouter();
            this.getRouter().getRoute("staffTracking").attachPatternMatched(this._onMaterialMatched, this);
		},
		_onMaterialMatched: function (oEvent) {
			this.showBusyIndicator();
			// this.showBusyIndicator(); //hide companyler gelince calisacak.
			this.getModel("staffModel").getData().showPassword = 0;
			this.getModel("staffModel").refresh();
			this.getStaff();
		},
		translateText: function (caption, insidevalue) {
			// read msg from i18n model
			var oBundle = this.getView().getModel("i18n").getResourceBundle();
			var sMsg = "";
			if (insidevalue) sMsg = oBundle.getText(caption, insidevalue);
			else sMsg = oBundle.getText(caption);
  
			if (sMsg) return sMsg;
			else return "";
		},

		openCreatePersonDialog: function () {
			this.showBusyIndicator(); //show fabrikalar gelince calisacak.
            this.getPlants(function (isOk) {
                if (!this._oDialog) {
                    this._oDialog = sap.ui.xmlfragment("createPersonDialog","workerapp.components.factory.components.staffmanagement.fragment.createPersonDialog", this);
                    this.getView().addDependent(this._oDialog);
                }
				this._oDialog.open();
				// this._oDialog.byId("DTP2").setMinDate(new Date());
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
		getClients: function (callback) {
            MaindataService.getClients().then(function (response) {
                var responseData = response.data;
                this.getModel("staffModel").getData().clients = responseData;
                this.getModel("staffModel").refresh();
                callback(true);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
		},
		getCompanies: function (callback) {
            MaindataService.getCompanies().then(function (response) {
                var responseData = response.data;
                this.getModel("staffModel").getData().companies = responseData;
                this.getModel("staffModel").refresh();
                callback(true);
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                callback(false);
                this.hideBusyIndicator();
            }.bind(this));
		},

		getStaff: function (){
            AuthService.getStaff().then(function (response) {
                var responseData = response.data;
                this.getModel("staffModel").getData().users = responseData;
                this.getModel("staffModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},

		closePersonDialog: function () {
			this._oDialog.close();
			this.clearPersonDialog();
			this.hideBusyIndicator();
		},
		
		clearPersonDialog: function() {
			var personDialogData = this.getModel("staffModel").getData();
			personDialogData.selectedRoleId = "";
			personDialogData.personMail= "";
			personDialogData.personName= "";
			personDialogData.personSurname= "";
			personDialogData.password= "";
			personDialogData.passwordRetry= "";
			personDialogData.personUserName= "";
			this.getModel("staffModel").refresh();
		},

		onSelectedRole: function(oEvent) {
			this.showBusyIndicator();
			var selectedRole = oEvent.getSource();
			var selectedRoleId = selectedRole.getSelectedKey();
			this.getModel("staffModel").setProperty("/selectedRoleId", selectedRoleId);
			if(selectedRoleId == 0){
				this.getCompanies(function(isOk){
					var assets = this.getModel("staffModel").getData().assets;
					assets = [];
					var companies = this.getModel("staffModel").getData().companies;
					for(var company of companies){
						assets.push({
							assetId:company.companyId,
							assetName:company.companyName
						});
					}
					this.getModel("staffModel").refresh();
				}.bind(this));

			}
			else if(selectedRoleId == 1){
				this.getClients(function(isOk){
					var assets = this.getModel("staffModel").getData().assets;
					assets = [];
					var clients = this.getModel("staffModel").getData().clients;
					for(var client of clients){
						assets.push({
							assetId:client.clientId,
							assetName:client.clientName
						});
					}
					this.getModel("staffModel").refresh();
				}.bind(this));

			}
			else if(selectedRoleId == 2 || selectedRoleId == 3){
				this.getPlants(function(isOk){
					var assets = this.getModel("staffModel").getData().assets;
					assets = [];
					var plants = this.getModel("staffModel").getData().plants;
					for(var plant of plants){
						assets.push({
							assetId:plant.plantId,
							assetName:plant.plantName
						});
					}
					this.getModel("staffModel").refresh();
				}.bind(this));

			}
			else {
				if(!selectedRoleId){
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
			}
		},

		createPerson: function () {
			var personData = this.getModel("staffModel").getData();
			var personadd = {
				areaId: personData.selectedRoleId,
				email: personData.personMail,
				firstName: personData.personName,
				lastName: personData.personSurname,
				password: personData.password,
				passwordRetry: personData.passwordRetry,
				username: personData.personUserName,
			};
			if(!personadd.areaId || !personadd.email || !personadd.firstName || !personadd.lastName || !personadd.password || !personadd.passwordRetry || !personadd.username ||
				personadd.email == " " || personadd.firstName == " " || personadd.lastName == " " || personadd.password == " " || personadd.passwordRetry == " " || personadd.username == " "){
				MessageBox.alert(this.translateText("MESSAGEERROREMPTY"), {
					icon: MessageBox.Icon.WARNING,
					title: this.translateText("ERROR"),
				});
			}
			else {
				if(personadd.password != personadd.passwordRetry){
					this.hideBusyIndicator();
					MessageBox.alert("Şifreler Uyuşmuyor. Lütfen kontrol ediniz...", {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR")
					});
				}
				else{
					if(personData.selectedRoleId == 0){
						AuthService.addCompanyOwner(personadd).then(function (response) {
							this.closePersonDialog();
							// callback(true);
							this.hideBusyIndicator();
							MessageBox.alert(this.translateText("MESSAGEADD"), {
								icon: MessageBox.Icon.INFORMATION,
								title: this.translateText("INFORMATION"),
							});
						}.bind(this)).catch(function () {
							// callback(false);
							this.hideBusyIndicator();
						}.bind(this));
					}
					else if(personData.selectedRoleId == 1){
						AuthService.addClientManager(personadd).then(function (response) {
							this.closePersonDialog();
							// callback(true);
							this.hideBusyIndicator();
							MessageBox.alert(this.translateText("MESSAGEADD"), {
								icon: MessageBox.Icon.INFORMATION,
								title: this.translateText("INFORMATION"),
							});
						}.bind(this)).catch(function () {
							// callback(false);
							this.hideBusyIndicator();
						}.bind(this));
			
					}
					else if(personData.selectedRoleId == 2){
						AuthService.addPlantManager(personadd).then(function (response) {
							this.closePersonDialog();
							// callback(true);
							this.hideBusyIndicator();
							MessageBox.alert(this.translateText("MESSAGEADD"), {
								icon: MessageBox.Icon.INFORMATION,
								title: this.translateText("INFORMATION"),
							});
						}.bind(this)).catch(function () {
							// callback(false);
							this.hideBusyIndicator();
						}.bind(this));
			
					}
					else if(personData.selectedRoleId == 3){
						AuthService.addOperator(personadd).then(function (response) {
							this.closePersonDialog();
							// callback(true);
							this.hideBusyIndicator();
							MessageBox.alert(this.translateText("MESSAGEADD"), {
								icon: MessageBox.Icon.INFORMATION,
								title: this.translateText("INFORMATION"),
							});
						}.bind(this)).catch(function () {
							// callback(false);
							this.hideBusyIndicator();
						}.bind(this));
			
					}
					else {
						if(!personData.selectedRoleId){
							MessageBox.alert(this.translateText("MESSAGEERROR"), {
								icon: MessageBox.Icon.WARNING,
								title: this.translateText("ERROR"),
							});
						}
					}
				}
			}
		},

		onListItemPress: function (oEvent) {
			var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
			
			var oItem = oEvent.getSource();
			var users = oItem.getBindingContext("staffModel").getObject();

			this.oRouter.navTo("staffDetail", {userId: users.id, layout: oNextUIState.layout});

			oItem.setNavigated(true);
			var oParent = oItem.getParent();
			// store index of the item clicked, which can be used later in the columnResize event
			this.iIndex = oParent.indexOfItem(oItem);
		},

		onSearch: function (oEvent) {
			var oTableSearchState = [],
				sQuery = oEvent.getParameter("query");

			if (sQuery && sQuery.length > 0) {
				oTableSearchState = [new Filter("username", FilterOperator.Contains, sQuery)];
			}

			this.getView().byId("staffTable").getBinding("items").filter(oTableSearchState, "Application");
		},

        toPage2: function () {
            var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(1);
            // ,
            // productPath = oEvent.getSource().getBindingContext("orderModel").getPath(),
            // product = productPath.split("/").slice(-1).pop();

             this.oRouter.navTo("StaffDetail", {layout: oNextUIState.layout});
		},

		onPressShowPassword: function () {
			
			if(clicked)
			{
				this.getModel("staffModel").getData().showPassword = 0;
				this.getModel("staffModel").refresh();
			}
			else
			{
				this.getModel("staffModel").getData().showPassword = 1;
				this.getModel("staffModel").refresh();
			}
			clicked = !clicked;
			
		}
	});
});