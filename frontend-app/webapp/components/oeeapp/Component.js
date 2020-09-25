sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component",
	'sap/ui/model/json/JSONModel',
	"sap/f/FlexibleColumnLayoutSemanticHelper"
], function(BaseComponent, Component, JSONModel, FlexibleColumnLayoutSemanticHelper) {
	"use strict";

	return BaseComponent.extend("workerapp.components.oeeapp.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);

			var oModel = new JSONModel();
			this.setModel(oModel, "orderModel");
			var oeeGlobalModel = new JSONModel({
				sideNavigationExpanded: true
			});
			this.setModel(oeeGlobalModel, "oeeGlobalModel");

			var oParentComponent = Component.getOwnerComponentFor(this);

			this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				if (!this.keycloak.authenticated || this.keycloak.isTokenExpired() || !(this.keycloak.hasRealmRole("COMPANY_OWNER") || this.keycloak.hasRealmRole("CLIENT_MANAGER") || this.keycloak.hasRealmRole("PLANT_MANAGER") || this.keycloak.hasRealmRole("OPERATOR"))) {
					oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
					this.getRouter().getHashChanger().replaceHash("");
				}
				this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().initialize();
		},

		getHelper: function () {
			var oFCL = this.getRootControl().byId("app"),
				oParams = jQuery.sap.getUriParameters(),
				oSettings = {
					defaultTwoColumnLayoutType: sap.f.LayoutType.TwoColumnsMidExpanded,
					defaultThreeColumnLayoutType: sap.f.LayoutType.ThreeColumnsMidExpanded,
					maxColumnsCount: oParams.get("max") ? oParams.get("max") : 3
				};

			return FlexibleColumnLayoutSemanticHelper.getInstanceFor(oFCL, oSettings);
		}
	});
});
