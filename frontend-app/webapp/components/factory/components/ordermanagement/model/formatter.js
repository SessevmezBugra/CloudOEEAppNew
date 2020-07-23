sap.ui.define([
	"sap/ui/core/format/NumberFormat"
], function (NumberFormat) {
	"use strict";

	

	var formatter = {
		statusText: function (sStatus) {
            var resourceBundle = this.getView().getModel("i18n").getResourceBundle();
            switch (sStatus) {
                case "NEW":
                    return resourceBundle.getText("NEW");
                case "ACT":
                    return resourceBundle.getText("ACT");
                case "HOLD":
					return resourceBundle.getText("HOLD");
				case "CMPL":
                    return resourceBundle.getText("CMPL");
                default:
                    return sStatus;
            }
		},
		statusStats: function (sStatus) {
            switch (sStatus) {
                case "NEW":
                    return "Success";
                case "ACT":
                    return "Indication05";
                case "HOLD":
					return "Warning";
				case "CMPL":
                    return "Error";
                default:
                    return sStatus;
            }
        }
	};

	return formatter;
});