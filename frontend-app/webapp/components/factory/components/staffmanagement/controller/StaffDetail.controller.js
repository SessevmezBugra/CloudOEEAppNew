sap.ui.define([
	"workerapp/base/BaseController",
	"workerapp/services/authservice",
	"sap/ui/model/json/JSONModel",
	"sap/m/MessageBox",
	'workerapp/components/factory/components/staffmanagement/model/formatter',
	"workerapp/services/maindataservice",
	"sap/ui/layout/form/SimpleForm",
	"sap/m/library",
], function (BaseController, AuthService, JSONModel, MessageBox, formatter, MaindataService, SimpleForm, mobileLibrary) {
	"use strict";

	var ButtonType = mobileLibrary.ButtonType;

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.StaffDetail", {
		formatter: formatter,

		onInit: function () {
			this.oRouter = this.getRouter();
			this.oModel = this.getModel("staffModel");

			this.oRouter.getRoute("staffDetail").attachPatternMatched(this._onMatched, this);
		},
		_onMatched: function (oEvent) {
			this.showBusyIndicator();
			this.userId = oEvent.getParameter("arguments").userId;
			this.getStaff();
			this.getStaffRoles();
		},

		getStaff: function () {
			AuthService.getStaffByUserId(this.userId).then(function (response) {
				this.getModel("staffModel").getData().userDetail = response.data;
				this.getModel("staffModel").refresh();
				this.hideBusyIndicator();
			}.bind(this)).catch(function () {
				this.hideBusyIndicator();
			}.bind(this));
		},

		getStaffRoles: function () {
			this.getModel("staffModel").getData().personRolesTableBusy = true;
			this.getModel("staffModel").refresh();
			AuthService.getStaffRolesByUserId(this.userId).then(function (response) {
				this.getModel("staffModel").getData().personRolesTableBusy = false;
				this.getModel("staffModel").getData().userRoles = response.data;
				this.getModel("staffModel").refresh();
				this.hideBusyIndicator();
			}.bind(this)).catch(function () {
				this.hideBusyIndicator();
			}.bind(this));
		},

		openAddRoleDialog: function () {
			if (!this._oDialog) {
				this._oDialog = sap.ui.xmlfragment("addRoleDialog", "workerapp.components.factory.components.staffmanagement.fragment.addRoleDialog", this);
				this.getView().addDependent(this._oDialog);
			}
			this._oDialog.open();
		},

		roleControl: function () {
			var staffData = this.getModel("staffModel").getData();
			for(var role of staffData.userRoles) {
				if(role.areaId == staffData.selectedAsset && role.userRole == staffData.selectedRole) {
					return false;
				}
			}
			return true;
		},

		addRole: function () {
			this._oDialog.close();
			this.showBusyIndicator();
			if(!this.roleControl()) {
				this.hideBusyIndicator();
				this._oDialog.close();
				this.clearRoleDialog();
				MessageBox.error(this.translateText("ROLE_ALREADY_EXIST"), {
					icon: MessageBox.Icon.ERROR,
					title: this.translateText("ERROR"),
				});
				return;
			}
			var staffData = this.getModel("staffModel").getData();
			var roleData = {
				areaId: staffData.selectedAsset,
				userEntity: {
					id: this.userId
				}
			}

			var roleService;

			if(staffData.selectedRole == "ORGANIZER") {
				roleService = AuthService.addCompanyOwnerRole;
			} else if(staffData.selectedRole == "CLIENT_MANAGER") {
				roleService = AuthService.addClientManagerRole;
			} else if(staffData.selectedRole == "PLANT_MANAGER") {
				roleService = AuthService.addPlantManagerRole;
			} else if(staffData.selectedRole == "OPERATOR") {
				roleService = AuthService.addOperatorRole;
			}

			roleService(roleData).then(function (response) {
				MessageBox.success(this.translateText("ROLE_ADDED"), {
					icon: MessageBox.Icon.INFORMATION,
					title: this.translateText("INFORMATION"),
				});
				this.hideBusyIndicator();
				this.getStaffRoles();
				this.clearRoleDialog();
			}.bind(this)).catch(function () {
				this.hideBusyIndicator();
			}.bind(this));
		},

		onSelectedRole: function (oEvent) {
			var assets = [];
			this.showBusyIndicator();
			var selectedRole = oEvent.getSource().getSelectedKey();
			this.getModel("staffModel").setProperty("/selectedRole", selectedRole);
			if (selectedRole == "ORGANIZER") {
				this.getCompanies(function (isOk) {
					var companies = this.getModel("staffModel").getData().companies;
					for (var company of companies) {
						assets.push({
							assetId: company.companyId,
							assetName: company.companyName
						});
					}
					this.getModel("staffModel").getData().assets = assets;
					this.getModel("staffModel").refresh();
				}.bind(this));
			}
			else if (selectedRole == "CLIENT_MANAGER") {
				this.getClients(function (isOk) {
					var clients = this.getModel("staffModel").getData().clients;
					for (var client of clients) {
						assets.push({
							assetId: client.clientId,
							assetName: client.clientName
						});
					}
					this.getModel("staffModel").getData().assets = assets;
					this.getModel("staffModel").refresh();
				}.bind(this));
			}
			else if (selectedRole == "PLANT_MANAGER" || selectedRole == "OPERATOR") {
				this.getPlants(function (isOk) {
					var plants = this.getModel("staffModel").getData().plants;
					for (var plant of plants) {
						assets.push({
							assetId: plant.plantId,
							assetName: plant.plantName
						});
					}
					this.getModel("staffModel").getData().assets = assets;
					this.getModel("staffModel").refresh();
				}.bind(this));
			}
			else {
				if (!selectedRole) {
					MessageBox.alert(this.translateText("MESSAGEERROR"), {
						icon: MessageBox.Icon.WARNING,
						title: this.translateText("ERROR"),
					});
				}
			}
		},

		clearRoleDialog: function () {
			var roleDialogData = this.getModel("staffModel").getData();
			roleDialogData.selectedRole = "";
			this.getModel("staffModel").refresh();
		},

		closeRoleDialog: function() {
			this._oDialog.close();
			this.clearRoleDialog();
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


		handleClose: function () {
			var sNextLayout = 'OneColumn';
			this.oRouter.navTo("staffTracking", { layout: sNextLayout });
		},
		openUpdatePersonDialog: function () {
			//this.showBusyIndicator(); //show fabrikalar gelince calisacak.
			var userDetailData = this.getModel("staffModel").getData().userDetail;
			this.getModel("staffModel").getData().personDialogData = {};
			var personDialogData = this.getModel("staffModel").getData().personDialogData;
			personDialogData.personName = userDetailData.firstName;
			personDialogData.personSurname = userDetailData.lastName;
			this.getModel("staffModel").refresh();
			if (!this._oDialog) {
				this._oDialog = sap.ui.xmlfragment("updatePersonDialog", "workerapp.components.factory.components.staffmanagement.fragment.updatePersonDialog", this);
				this.getView().addDependent(this._oDialog);
			}
			this._oDialog.open();
			// this._oDialog.byId("DTP2").setMinDate(new Date());	
		},

		updatePerson: function () {
			this.showBusyIndicator();
			var staffData = this.getModel("staffModel").getData();
			var personadd = {
				userId: this.userId,
				firstName: staffData.personDialogData.personName,
				lastName: staffData.personDialogData.personSurname
			};
			if (!personadd.firstName || !personadd.lastName ||
				personadd.firstName == "" || personadd.lastName == "") {
				MessageBox.alert(this.translateText("MESSAGEERROREMPTY"), {
					icon: MessageBox.Icon.WARNING,
					title: this.translateText("ERROR"),
				});
			}
			else {
				AuthService.updateStaff(personadd).then(function (response) {
					this.closeUpdatePersonDialog();
					this.getStaff();
					this.hideBusyIndicator();
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

		openRemoveRoleDialog(oEvent) {
			this.removedRole = oEvent.getSource().getBindingContext("staffModel").getObject();
			var oBundle = this.getView().getModel("i18n").getResourceBundle();
            this._removeDialog = new sap.m.Dialog({
                title: oBundle.getText("REMOVE_ROLE"),
                content: new SimpleForm({
                    content: [
                        new sap.m.Text({
                            wrapping: true,
                            wrappingType: sap.m.WrappingType.Hyphenated,
                            text: oBundle.getText("REMOVE_ROLE_QUESTION")
                        }),
                    ]
                }),
                beginButton: new sap.m.Button({
                    type: ButtonType.Emphasized,
                    text: oBundle.getText("REMOVE"),
                    press: function () {
                        this.removeRole();
                    }.bind(this)
                }),
                endButton: new sap.m.Button({
                    text: oBundle.getText("CANCEL"),
                    press: function () {
                       this._removeDialog.close();
                    }.bind(this)
                })
            });
            this.getView().addDependent(this._removeDialog);
            this._removeDialog.open();
		},
		
		removeRole: function() {
			this._removeDialog.close();
			this.getModel("staffModel").getData().personRolesTableBusy = true;
			this.getModel("staffModel").refresh();
			AuthService.removeRole(this.removedRole.id).then(function (response) {
				this.getStaffRoles();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
				this.getModel("staffModel").getData().personRolesTableBusy = false;
				this.getModel("staffModel").refresh();
                this.hideBusyIndicator();
            }.bind(this));
		},

		openDeleteUserDialog: function() {
			
			var oBundle = this.getView().getModel("i18n").getResourceBundle();
            this._deleteUserDialog = new sap.m.Dialog({
                title: oBundle.getText("DELETE_USER"),
                content: new SimpleForm({
                    content: [
                        new sap.m.Text({
                            wrapping: true,
                            wrappingType: sap.m.WrappingType.Hyphenated,
                            text: oBundle.getText("DELETE_USER_QUESTION")
                        }),
                    ]
                }),
                beginButton: new sap.m.Button({
                    type: ButtonType.Emphasized,
                    text: oBundle.getText("REMOVE"),
                    press: function () {
                        this.removeUser();
                    }.bind(this)
                }),
                endButton: new sap.m.Button({
                    text: oBundle.getText("CANCEL"),
                    press: function () {
                       this._deleteUserDialog.close();
                    }.bind(this)
                })
            });
            this.getView().addDependent(this._deleteUserDialog);
            this._deleteUserDialog.open();
		},

		removeUser: function() {
			this._deleteUserDialog.close();
			this.showBusyIndicator();
			AuthService.removeUser(this.userId).then(function (response) {
				this.handleClose();
                this.hideBusyIndicator();
            }.bind(this)).catch(function () {
                this.hideBusyIndicator();
            }.bind(this));
		}
		
	});
});