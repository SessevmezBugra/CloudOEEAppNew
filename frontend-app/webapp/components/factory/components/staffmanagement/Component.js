sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component",
	'sap/ui/model/json/JSONModel',
	"sap/f/FlexibleColumnLayoutSemanticHelper"
], function(BaseComponent, Component, JSONModel, FlexibleColumnLayoutSemanticHelper) {
	"use strict";

	return BaseComponent.extend("workerapp.components.factory.components.staffmanagement.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);

			var oModel = new JSONModel({
				assets:[]
			}); 
			this.setModel(oModel, "staffModel");

			var oParentComponent = Component.getOwnerComponentFor(Component.getOwnerComponentFor(this));
			
			// this.getRouter().attachBeforeRouteMatched(function (oEvent) {
			// 	if (!this.keycloak.authenticated || this.keycloak.isTokenExpired()) {
			// 		oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
			// 	}
			// 	this.hideBusyIndicator();
			// }.bind(this));

			this.getRouter().initialize();
		},
		getHelper: function () {
			var oFCL = this.getRootControl().byId("app"),
				oParams = jQuery.sap.getUriParameters(),
				oSettings = {
					defaultTwoColumnLayoutType: sap.f.LayoutType.TwoColumnsMidExpanded,
					//defaultThreeColumnLayoutType: sap.f.LayoutType.ThreeColumnsMidExpanded,
					maxColumnsCount: oParams.get("max") ? oParams.get("max") : 2
				};

			return FlexibleColumnLayoutSemanticHelper.getInstanceFor(oFCL, oSettings);
		}
	});
});
