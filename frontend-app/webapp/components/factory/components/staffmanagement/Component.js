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

			var oModel = new JSONModel();
			this.setModel(oModel, "staffModel");

			var oParentComponent = Component.getOwnerComponentFor(Component.getOwnerComponentFor(this));
			// await this.getRouter().attachBeforeRouteMatched(async function (oEvent){
			// 	await this.UserService.initCheckSSO().then(function(isValid) {
			// 		if(!isValid){
			// 			oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
			// 		}
			// 	}.bind(this));
			// 	this.hideBusyIndicator();
			// }.bind(this), this);

			this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				if (!this.UserService.getKeycloak().authenticated || this.UserService.getKeycloak().isTokenExpired()) {
					oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
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
					//defaultThreeColumnLayoutType: sap.f.LayoutType.ThreeColumnsMidExpanded,
					maxColumnsCount: oParams.get("max") ? oParams.get("max") : 2
				};

			return FlexibleColumnLayoutSemanticHelper.getInstanceFor(oFCL, oSettings);
		}
	});
});
