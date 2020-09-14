sap.ui.define([
    "sap/ui/core/format/NumberFormat",
    "sap/ui/core/format/DateFormat"
], function (NumberFormat, DateFormat) {
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
        },

        dateFormat: function (oDate) {
            var oDateFormat = DateFormat.getDateTimeInstance({pattern: "dd/MM/yyyy HH:mm"});
            if(oDate) {
                return oDateFormat.format(new Date(oDate));
            } else {
                return "";
            }
        } 
	};

	return formatter;
});