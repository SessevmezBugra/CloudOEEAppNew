sap.ui.define([
	"workerapp/base/BaseComponent",
	"sap/ui/core/Component"
], function(BaseComponent, Component) {
	"use strict";

	return BaseComponent.extend("workerapp.components.factory.components.fastmenu.Component", {
		metadata: {
			manifest: "json"
		},
		
		init: async function() {
			BaseComponent.prototype.init.apply(this, arguments);

			var oParentComponent = Component.getOwnerComponentFor(Component.getOwnerComponentFor(this));

			this.getRouter().attachBeforeRouteMatched(function (oEvent) {
				if (!this.keycloak.authenticated || this.keycloak.isTokenExpired()) {
					oParentComponent.getRouter().navTo("home", {}, true /*no history*/);
				}
				this.hideBusyIndicator();
			}.bind(this));

			this.getRouter().initialize();
		}
	});
});
