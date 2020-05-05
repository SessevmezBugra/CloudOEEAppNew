sap.ui.define([
    "workerapp/base/BaseController",
    "workerapp/services/userservice",
	"sap/ui/model/json/JSONModel",
    'sap/ui/model/SimpleType',
    'sap/ui/model/ValidateException',
    'jquery.sap.global',
    'sap/m/MessageBox',
    "workerapp/model/LocalStorageModel",
], function (BaseController, UserService, JSONModel, SimpleType, ValidateException, jQuery, MessageBox, LocalStorageModel) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.Login", {
		onInit: function () {
            this.oView = this.getView();
            var oViewModel = new JSONModel({
                userInfo : {
                    username: "",
                    password: ""
                }
			});
            this.setModel(oViewModel, "loginModel");
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("loginUsername"), true);
            sap.ui.getCore().getMessageManager().registerObject(this.oView.byId("loginPassword"), true);
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
				var rexPass = "^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})";
				if (!oValue.match(rexPass)) {
					throw new ValidateException("Lutfen gecerli bir sifre giriniz");
				}
			}
        }),

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
        
        login: function() {
            this.showBusyIndicator();
			var that = this;
			var oView = this.getView();
			var aInputs = [
				oView.byId("loginUsername"),
				oView.byId("loginPassword")
			];
			var bValidationError = false;

			// check that inputs are not empty
			// this does not happen during data binding as this is only triggered by changes
			jQuery.each(aInputs, function (i, oInput) {
				bValidationError = that._validateInput(oInput) || bValidationError;
			});

			// output result
			if (!bValidationError) {
				var data = this.getModel("loginModel").getData();
                UserService.login(data.userInfo).then(function(response) {
                    var localUserModel = new LocalStorageModel("localUserModel");
                    localUserModel.setData({
                        userInfo: {
                            username: data.userInfo.username//Bu daha sonra kullanicinin adi ve soy adi olarak degistirilecek ve servisten donen datadan alinacak
                        },
                        jwt: response.request.getResponseHeader("Authorization")
                    });
                    this.getParentComponent(this.getOwnerComponent()).setModel(localUserModel, "localUserModel");
					this.hideBusyIndicator();
					this.getParentComponent(this.getOwnerComponent()).getRouter().navTo("factory", {}, true /*no history*/);
				}.bind(this)).catch(function(error) {
					this.hideBusyIndicator();
					MessageBox.alert(this.getResourceBundle().getText("LOGIN_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO"), {
						icon: MessageBox.Icon.WARNING,
						title: this.getResourceBundle().getText("LOGIN_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
					});
					console.log(error);
				}.bind(this));
			} else {
				this.hideBusyIndicator();
				MessageBox.alert(this.getResourceBundle().getText("LOGIN_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO"), {
					icon: MessageBox.Icon.WARNING,
					title: this.getResourceBundle().getText("LOGIN_MESSAGE_BOX_PLEASE_CONTROL_YOUR_INFO_TITLE"),
				});
			}
        },

        goToRegisterView: function() {
			this.getRouter().navTo("register", {}, true /*no history*/);
        }
	});
});