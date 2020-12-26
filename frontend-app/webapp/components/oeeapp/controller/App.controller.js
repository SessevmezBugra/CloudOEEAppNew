sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/Device',
	'sap/ui/core/syncStyleClass',
	'sap/m/library',
	'sap/m/MessageToast',
	'sap/m/ActionSheet',
	'sap/m/Button',
	"sap/ui/core/routing/HashChanger",
	'workerapp/components/oeeapp/model/formatter',
], function (BaseController, Device, syncStyleClass, mobileLibrary, MessageToast, ActionSheet, Button, HashChanger, formatter) {
	"use strict";

	var ButtonType = mobileLibrary.ButtonType;

	return BaseController.extend("workerapp.components.oeeapp.controller.App", {

		_bExpanded: true,

		formatter: formatter,

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
			this.oRouter = this.getRouter();
			this.getRouter().attachRouteMatched(this.onRouteMatched, this);
			this.getRouter().attachBeforeRouteMatched(this.onBeforeRouteMatched, this);
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

		goFactory: function () {
			var oComponentTargetInfo = {};
				oComponentTargetInfo["factory"] = {
					route: "fastMenu",
					parameters: {}
				}
			var rootComponent = this.getParentComponent(this.getOwnerComponent());
			rootComponent.getRouter().navTo("factory", {}, oComponentTargetInfo);
		},

		onItemSelect: function (oEvent) {
			var level = oEvent.getParameter('item').getLevel();
			if (level == 0) {
				if("oeeApp" == oEvent.getParameter('item').getKey()) {
					var rootComponent = this.getParentComponent(this.getOwnerComponent());
					rootComponent.getRouter().navTo("oeeapp");
				}else {
					this.getRouter().navTo(oEvent.getParameter('item').getKey());
				}
				
			} else {
				var oComponentTargetInfo = {};
				oComponentTargetInfo[oEvent.getParameter('item').getParent().getKey()] = {
					route: oEvent.getParameter('item').getKey(),
					parameters: {}
				}
				this.getRouter().navTo(oEvent.getParameter('item').getParent().getKey(), {}, oComponentTargetInfo);
			}
		},

		onBeforeRouteMatched: function(oEvent) {
			var oModel = this.getModel("orderModel");

			var sLayout = oEvent.getParameters().config.layout;

			if (!sLayout) {
				sLayout = oEvent.getParameters().arguments.layout;
			}
			// If there is no layout parameter, query for the default level 0 layout (normally OneColumn)
			if (!sLayout) {
				var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(0);
				sLayout = oNextUIState.layout;
			}

			// Update the layout of the FlexibleColumnLayout
			if (sLayout) {
				oModel.setProperty("/layout", sLayout);
			}
        },
        
		onColumnResize: function(oEvent) {
			// This event is ideal to call scrollToIndex function of the Table
			// var oMasterView = oEvent.getSource().getBeginColumnPages()[0];

			// if (oMasterView.getController().iIndex) {
			// 	var oTable = oMasterView.byId("ordersTable");
			// 	oTable.scrollToIndex(oMasterView.getController().iIndex);
			// }

		},
		onRouteMatched: function (oEvent) {
			var oModel = this.getModel("orderModel");

			var sLayout = oEvent.getParameters().config.layout;

			if (!sLayout) {
				sLayout = oEvent.getParameters().arguments.layout;
			}
			// If there is no layout parameter, query for the default level 0 layout (normally OneColumn)
			if (!sLayout) {
				var oNextUIState = this.getOwnerComponent().getHelper().getNextUIState(0);
				sLayout = oNextUIState.layout;
			}

			// Update the layout of the FlexibleColumnLayout
			if (sLayout) {
				oModel.setProperty("/layout", sLayout);
			}
			var sRouteName = oEvent.getParameter("name"),
				oArguments = oEvent.getParameter("arguments");

			this._updateUIElements();

			// Save the current route name
			this.currentRouteName = sRouteName;
			// this.currentProduct = oArguments.product;
			// this.currentSupplier = oArguments.supplier;
		},

		onStateChanged: function (oEvent) {

			var bIsNavigationArrow = oEvent.getParameter("isNavigationArrow"),
				sLayout = oEvent.getParameter("layout");

			this._updateUIElements();

			// Replace the URL with the new layout if a navigation arrow was used
			if (bIsNavigationArrow) {
				this.oRouter.navTo(this.currentRouteName, {layout: sLayout}, true);
			}
		},

		// Update the close/fullscreen buttons visibility
		_updateUIElements: function () {
			var oModel = this.getModel("orderModel");
			var oUIState = this.getOwnerComponent().getHelper().getCurrentUIState();
			// oModel.setData(oUIState);
		},

		onExit: function () {
			this.oRouter.detachRouteMatched(this.onRouteMatched, this);
			this.oRouter.detachBeforeRouteMatched(this.onBeforeRouteMatched, this);
		}

	});
});
