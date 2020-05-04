sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/userservice",
	"sap/ui/model/json/JSONModel",
    'sap/ui/model/SimpleType',
    'sap/ui/model/ValidateException',
    'jquery.sap.global',
    'sap/m/MessageBox'
], function (BaseController, UserService, JSONModel, SimpleType, ValidateException, jQuery, MessageBox) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.Register", {

		onInit: function () {
            this.oView = this.getView();
            var oViewModel = new JSONModel({
                workerInfo : {
                    username: "",
                    password: "",
                    passwordRetry: ""
                },
                ownerInfo : {
                    username: "",
                    password: "",
                    passwordRetry: "",
                    companyName: ""
                }
			});
            this.setModel(oViewModel, "registerModel");

            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("ownerUsername"), true);
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("ownerPassword"), true);
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("ownerPasswordRetry"), true);
            // sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("ownerCompanyName"), true);
            
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("workerUsername"), true);
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("workerPassword"), true);
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("workerPasswordRetry"), true);
        },
        
        _validateInput: function(oInput) {
			var oBinding = oInput.getBinding("value");
			var sValueState = "None";
			var bValidationError = false;

			try {
				if(oBinding.getType()){
					oBinding.getType().validateValue(oInput.getValue());
				}
				else {
					if(oInput.data("inputId") == "ownerPasswordRetry"){
						bValidationError = this.customPasswordRetryTypeForOwner()
						if(bValidationError){
							sValueState = "Error";
						}
					}
					if(oInput.data("inputId") == "workerPasswordRetry"){
						bValidationError = this.customPasswordRetryTypeForWorker()
						if(bValidationError){
							sValueState = "Error";
						}
					}
				}

			} catch (oException) {
				sValueState = "Error";
				bValidationError = true;
			}

			oInput.setValueState(sValueState);

			return bValidationError;
        },
        
        onChange: function(oEvent) {
			var oInput = oEvent.getSource();
			this._validateInput(oInput);
        },
        
        customEMailType : SimpleType.extend("email", {
			formatValue: function (oValue) {
				return oValue;
			},
			parseValue: function (oValue) {
				//parsing step takes place before validating step, value could be altered here
				return oValue;
			},
			validateValue: function (oValue) {
				// The following Regex is NOT a completely correct one and only used for demonstration purposes.
				// RFC 5322 cannot even checked by a Regex and the Regex for RFC 822 is very long and complex.
				var rexMail = /^\w+[\w-+\.]*\@\w+([-\.]\w+)*\.[a-zA-Z]{2,}$/;
				if (!oValue.match(rexMail)) {
					throw new ValidateException("Lutfen gecerli bir mail giriniz");
				}
			}
        }),

        customPasswordType: SimpleType.extend("password", {
			formatValue: function (oValue) {
				return oValue;
			},
			parseValue: function (oValue) {
				//parsing step takes place before validating step, value could be altered here
				return oValue;
			},
			validateValue: function (oValue) {
				// The following Regex is NOT a completely correct one and only used for demonstration purposes.
				// RFC 5322 cannot even checked by a Regex and the Regex for RFC 822 is very long and complex.
				var rexPass = '^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,})';
				if (!oValue.match(rexPass)) {
					throw new ValidateException("Lutfen gecerli bir sifre giriniz");
				}
			}
        }),

        customPasswordRetryTypeForOwner: function() {
			var error = false;
			if(this.getModel("registerModel").getData().ownerInfo.password != this.getModel("registerModel").getData().ownerInfo.passwordRetry){
				error = true;
			}
			return error;
		},
		customPasswordRetryTypeOnChangeForOwner: function(oEvent) {
			if(this.getModel("registerModel").getData().ownerInfo.password != oEvent.getParameter("value")){
				oEvent.getSource().setValueState("Error");
			}else {
				oEvent.getSource().setValueState("None");
			}
		},
		customPasswordRetryTypeForWorker: function() {
			var error = false;
			if(this.getModel("registerModel").getData().workerInfo.password != this.getModel("registerModel").getData().workerInfo.passwordRetry){
				error = true;
			}
			return error;
		},
		customPasswordRetryTypeOnChangeForWorker: function(oEvent) {
			if(this.getModel("registerModel").getData().workerInfo.password != oEvent.getParameter("value")){
				oEvent.getSource().setValueState("Error");
			}else {
				oEvent.getSource().setValueState("None");
			}
		},
        customCompanyNameType : SimpleType.extend("companyName", {
			formatValue: function (oValue) {
				return oValue;
			},
			parseValue: function (oValue) {
				//parsing step takes place before validating step, value could be altered here
				return oValue;
			},
			validateValue: function (oValue) {
				// The following Regex is NOT a completely correct one and only used for demonstration purposes.
				// RFC 5322 cannot even checked by a Regex and the Regex for RFC 822 is very long and complex.
				
				if (oValue.lenght < 3 || oValue.lenght > 10) {
					throw new ValidateException("Lutfen gecerli bir sirket adi giriniz");
				}
			}
        }),

        registerWorker : function () {
			// collect input controls
			this.showBusyIndicator();
			var that = this;
			var oView = this.getView();
			var aInputs = [
				oView.byId("workerUsername"),
				oView.byId("workerPassword"),
				oView.byId("workerPasswordRetry")
			];
			var bValidationError = false;

			// check that inputs are not empty
			// this does not happen during data binding as this is only triggered by changes
			jQuery.each(aInputs, function (i, oInput) {
				bValidationError = that._validateInput(oInput) || bValidationError;
			});

			// output result
			if (!bValidationError) {
				var data = this.getModel("registerModel").getData();
                UserService.createWorkerUser(data.workerInfo).then(function(response) {
					this.hideBusyIndicator();
					MessageBox.success(this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_REGISTER_IS_SUCCESS"), {
						title: this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_REGISTER_IS_SUCCESS_TITLE"),
						actions: [this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_REGISTER_IS_SUCCESS_ACTION_BTN")],
						emphasizedAction: this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_REGISTER_IS_SUCCESS_ACTION_BTN"),
						onClose: function (sAction) {
							this.getRouter().navTo("login");
						}.bind(this)
					});
					console.log(response);
				}.bind(this)).catch(function(error) {
					this.hideBusyIndicator();
					MessageBox.alert(error.responseJSON.message, {
						icon: MessageBox.Icon.WARNING,
						title: this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
					});
					console.log(error);
				}.bind(this));
			} else {
				this.hideBusyIndicator();
				MessageBox.alert(this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO"), {
					icon: MessageBox.Icon.WARNING,
					title: this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
				});
			}
        },
        
        registerOwner : function () {
			this.showBusyIndicator();
			// collect input controls
			var that = this;
			var oView = this.getView();
			var aInputs = [
				oView.byId("ownerUsername"),
				oView.byId("ownerPassword"),
				oView.byId("ownerPasswordRetry"),
                oView.byId("ownerCompanyName")
			];
			var bValidationError = false;

			// check that inputs are not empty
			// this does not happen during data binding as this is only triggered by changes
			jQuery.each(aInputs, function (i, oInput) {
				bValidationError = that._validateInput(oInput) || bValidationError;
			});

			// output result
			if (!bValidationError) {
				var data = this.getModel("registerModel").getData();
                UserService.createOwnerUser(data.ownerInfo).then(function(response) {
					this.hideBusyIndicator();
					MessageBox.success(this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_REGISTER_IS_SUCCESS"), {
						title: this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_REGISTER_IS_SUCCESS_TITLE"),
						actions: [this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_REGISTER_IS_SUCCESS_ACTION_BTN")],
						emphasizedAction: this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_REGISTER_IS_SUCCESS_ACTION_BTN"),
						onClose: function (sAction) {
							this.getRouter().navTo("login", {}, true /*no history*/);
						}.bind(this)
					});
					console.log(response);
				}.bind(this)).catch(function(error) {
					this.hideBusyIndicator();
					MessageBox.alert(error.responseJSON.message, {
						icon: MessageBox.Icon.WARNING,
						title: this.getResourceBundle().getText("REGISTER_WORKER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
					});
					console.log(error);
				}.bind(this));
			} else {
				this.hideBusyIndicator();
				MessageBox.alert(this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO"), {
					icon: MessageBox.Icon.WARNING,
					title: this.getResourceBundle().getText("REGISTER_OWNER_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
				});
			}
		},

		goToLoginView: function(){
			// this.getRouter().navTo("login");
			this.getRouter().navTo("login", {}, true /*no history*/);
		}
		
    });
});