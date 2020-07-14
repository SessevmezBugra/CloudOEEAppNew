sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/Device',
	'sap/ui/core/syncStyleClass',
	'sap/m/library',
	'sap/m/MessageToast',
	'sap/m/ActionSheet',
	'sap/m/Button',
	"sap/ui/core/routing/HashChanger"
], function (BaseController, Device, syncStyleClass, mobileLibrary, MessageToast, ActionSheet, Button, HashChanger) {
	"use strict";

	var ButtonType = mobileLibrary.ButtonType;

	return BaseController.extend("workerapp.components.factory.controller.App", {

		_bExpanded: true,

		onInit: function () {
			this.getView().addStyleClass(this.getOwnerComponent().getContentDensityClass());

			// if the app starts on desktop devices with small or meduim screen size, collaps the sid navigation
			if (Device.resize.width <= 1024) {
				this.onSideNavButtonPress();
			}
			Device.media.attachHandler(function (oDevice) {
				if ((oDevice.name === "Tablet" && this._bExpanded) || oDevice.name === "Desktop") {
					this.onSideNavButtonPress();
					// set the _bExpanded to false on tablet devices
					// extending and collapsing of side navigation should be done when resizing from
					// desktop to tablet screen sizes)
					this._bExpanded = (oDevice.name === "Desktop");
				}
			}.bind(this));
		},

		onSideNavButtonPress: function () {
			var oToolPage = this.byId("toolPage");
			var bSideExpanded = oToolPage.getSideExpanded();
			this._setToggleButtonTooltip(bSideExpanded);
			oToolPage.setSideExpanded(!oToolPage.getSideExpanded());
		},

		_setToggleButtonTooltip: function (bSideExpanded) {
			var oToggleButton = this.byId('sideNavigationToggleButton');
			if (bSideExpanded) {
				oToggleButton.setTooltip('Large Size Navigation');
			} else {
				oToggleButton.setTooltip('Small Size Navigation');
			}
		},

		onUserNamePress: function (oEvent) {
			var oBundle = this.getModel("i18n").getResourceBundle();
			if (this.getView().byId("userMessageActionSheet")) {
				this.getView().byId("userMessageActionSheet").destroy();
				return;
			}
			var logoutText = oBundle.getText("TOOLPAGE_LOGOUT_BTN_TITLE");
			var oActionSheet = new ActionSheet(this.getView().createId("userMessageActionSheet"), {
				title: oBundle.getText("TOOLPAGE_USERNAME_BTN_TITLE"),
				showCancelButton: false,
				buttons: [
					new Button({
						text: logoutText,
						type: ButtonType.Transparent,
						press: this.logout.bind(this)
					}),
				],
				afterClose: function () {
					oActionSheet.destroy();
				}
			});
			// forward compact/cozy style into dialog
			syncStyleClass(this.getView().getController().getOwnerComponent().getContentDensityClass(), this.getView(), oActionSheet);
			oActionSheet.openBy(oEvent.getSource());
		},

		logout: function() {
			// this.getParentComponent(this.getOwnerComponent()).getModel("localUserModel").setData(null)
			// var rootComponent = this.getParentComponent(this.getOwnerComponent());
			// rootComponent.getRouter().navTo("home");
			this.UserService.logout();
		},

		onItemSelect: function (oEvent) {
			var level = oEvent.getParameter('item').getLevel();
			if (level == 0) {
				this.getRouter().navTo(oEvent.getParameter('item').getKey());
			} else {
				var oComponentTargetInfo = {};
				oComponentTargetInfo[oEvent.getParameter('item').getParent().getKey()] = {
					route: oEvent.getParameter('item').getKey(),
					parameters: {}
				}
				this.getRouter().navTo(oEvent.getParameter('item').getParent().getKey(), {}, oComponentTargetInfo);
			}
		}

	});
});
