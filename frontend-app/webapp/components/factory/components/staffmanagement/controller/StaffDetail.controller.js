sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/authservice",
	"sap/ui/model/json/JSONModel",
	"sap/m/MessageBox"
], function(BaseController, AuthService, JSONModel, MessageBox) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffDetail", {
		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("staffModel");

			this.oRouter.getRoute("staffDetail").attachPatternMatched(this._onMatched, this);
		},
		_onMatched: function (oEvent) {
			this.showBusyIndicator();
			this.userId = oEvent.getParameter("arguments").userId;
			this.getStaff();
		},

		getStaff: function (){
            AuthService.getStaffByUserId(this.userId).then(function (response) {
				this.getModel("staffModel").getData().detailUsers = response.data;
                this.getModel("staffModel").refresh();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		},
		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("staffTracking", {layout: sNextLayout});
		},
		openUpdatePersonDialog: function () {
			//this.showBusyIndicator(); //show fabrikalar gelince calisacak.
            if (!this._oDialog) {
                this._oDialog = sap.ui.xmlfragment("updatePersonDialog","workerapp.components.factory.components.staffmanagement.fragment.updatePersonDialog", this);
                this.getView().addDependent(this._oDialog);
            }
			this._oDialog.open();
			// this._oDialog.byId("DTP2").setMinDate(new Date());	
		},
		closeUpdatePersonDialog: function () {
			this._oDialog.close();
			this.clearPersonDialog();
			this.hideBusyIndicator();
		},
		
		clearPersonDialog: function() {
			var personDialogData = this.getModel("staffModel").getData();
			personDialogData.personMail= "";
			personDialogData.personName= "";
			personDialogData.personSurname= "";
			personDialogData.personUserName= "";
			this.getModel("staffModel").refresh();
		},
		
		updatePerson: function () {
			var personData = this.getModel("staffModel").getData();
			var personadd = {
				userId: this.userId,
				firstName: personData.personName,
				lastName: personData.personSurname
			};
			if(!personadd.firstName || !personadd.lastName || 
				personadd.firstName == " " || personadd.lastName == " "){
				MessageBox.alert(this.translateText("MESSAGEERROREMPTY"), {
					icon: MessageBox.Icon.WARNING,
					title: this.translateText("ERROR"),
				});
			}
			else {
				AuthService.updateStaff(personadd).then(function (response) {
					this.closeUpdatePersonDialog();
					this.getStaff();
					MessageBox.alert(this.translateText("MESSAGEUPDATE"), {
						icon: MessageBox.Icon.INFORMATION,
						title: this.translateText("INFORMATION")
					});
					this.getModel("staffModel").refresh();
				}.bind(this)).catch(function () {
					this.hideBusyIndicator();
				}.bind(this));
			}
		},
	});
});