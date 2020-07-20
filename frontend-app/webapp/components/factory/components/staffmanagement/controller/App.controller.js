sap.ui.define([
	"workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel",
	"sap/f/FlexibleColumnLayoutSemanticHelper"
], function(BaseController, JSONModel, FlexibleColumnLayoutSemanticHelper) {
	"use strict";

	return BaseController.extend("workerapp.components.factory.components.staffmanagement.controller.App", {

		onInit: function () {
			
			this.oRouter = this.getRouter();
			this.getRouter().attachRouteMatched(this.onRouteMatched, this);
			this.getRouter().attachBeforeRouteMatched(this.onBeforeRouteMatched, this);
			
		},

		onBeforeRouteMatched: function(oEvent) {
			var oModel = this.getModel("staffModel");

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
			//var oMasterView = oEvent.getSource().getBeginColumnPages()[0];

			//if (oMasterView.getController().iIndex) {
			//	var oTable = oMasterView.byId("staffTable");
			//	oTable.scrollToIndex(oMasterView.getController().iIndex);
			//}

		},
		onRouteMatched: function (oEvent) {
			var oModel = this.getModel("staffModel");

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
			var oModel = this.getModel("staffModel");
			var oUIState = this.getOwnerComponent().getHelper().getCurrentUIState();
			// oModel.setData(oUIState);
		},

		onExit: function () {
			this.oRouter.detachRouteMatched(this.onRouteMatched, this);
			this.oRouter.detachBeforeRouteMatched(this.onBeforeRouteMatched, this);
		}

	});
});
