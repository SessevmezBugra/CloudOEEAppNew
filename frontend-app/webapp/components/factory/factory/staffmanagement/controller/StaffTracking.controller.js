sap.ui.define([
	"workerapp/base/BaseController",
	'sap/ui/model/json/JSONModel',
	'sap/ui/Device',
	'workerapp/model/formatter'
], function (BaseController, JSONModel, Device, formatter) {
	"use strict";
	return BaseController.extend("workerapp.components.home.components.staffmanagement.controller.StaffTracking", {
		formatter: formatter,

		onInit: function () {
			
		},
	});
});