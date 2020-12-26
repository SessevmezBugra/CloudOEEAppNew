sap.ui.define([
	"sap/ui/core/UIComponent",
	'sap/ui/core/BusyIndicator',
	"workerapp/model/LocalStorageModel",
	"workerapp/model/models",
	"sap/ui/Device",
	"workerapp/services/userservice"
], function(UIComponent, BusyIndicator, LocalStorageModel, models, Device, UserService) {
	"use strict";

	return UIComponent.extend("workerapp.base.BaseComponent", {
		
		
		UserService : UserService,

		init: function() {
			UIComponent.prototype.init.apply(this, arguments);
			var localUserModel = new LocalStorageModel("localUserModel");
			this.setModel(localUserModel,"localUserModel");
			this.setModel(models.createDeviceModel(), "device");
			this.keycloak = this.UserService.getKeycloak();
		},

		hideBusyIndicator : function() {
			BusyIndicator.hide();
		},

		showBusyIndicator : function (iDuration, iDelay) {
			BusyIndicator.show(iDelay);

			if (iDuration && iDuration > 0) {
				if (this._sTimeoutId) {
					clearTimeout(this._sTimeoutId);
					this._sTimeoutId = null;
				}

				this._sTimeoutId = setTimeout(function() {
					this.hideBusyIndicator();
				}.bind(this), iDuration);
			}
		},

		getContentDensityClass : function() {
			if (this._sContentDensityClass === undefined) {
				// check whether FLP has already set the content density class; do nothing in this case
				// eslint-disable-next-line sap-no-proprietary-browser-api
				if (document.body.classList.contains("sapUiSizeCozy") || document.body.classList.contains("sapUiSizeCompact")) {
					this._sContentDensityClass = "";
				} else if (!Device.support.touch) { // apply "compact" mode if touch is not supported
					this._sContentDensityClass = "sapUiSizeCompact";
				} else {
					// "cozy" in case of touch support; default for most sap.m controls, but needed for desktop-first controls like sap.ui.table.Table
					this._sContentDensityClass = "sapUiSizeCozy";
				}
			}
			return this._sContentDensityClass;
		},

		validateToken: function(){
			return new Promise(function (resolve) {
				UserService.validateToken().then(function(response) {
					resolve(true);
				}.bind(this)).catch(function(error) {
					this.getModel("localUserModel").setData(null);
					resolve(false);
				}.bind(this));
			}.bind(this));
		},

		getParentComponent: function(component) {
			return Component.getOwnerComponentFor(component);
		}
	});
});
