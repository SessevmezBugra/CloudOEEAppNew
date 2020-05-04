sap.ui.define([
    "workerapp/base/BaseController",
	"sap/ui/model/json/JSONModel",
], function (BaseController, JSONModel) {
	"use strict";

	return BaseController.extend("workerapp.components.home.controller.Home", {

		onInit: function () {
            this.oView = this.getView();
           
        },
		
    });
});