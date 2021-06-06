sap.ui.define([
    "sap/ui/core/format/NumberFormat",
    "sap/ui/core/format/DateFormat"
], function (NumberFormat, DateFormat) {
	"use strict";

	var formatter = {
        
        roleDesc: function(role) {
            var resourceBundle = this.getView().getModel("i18n").getResourceBundle();
            switch (role) {
                case "ORGANIZER":
                    return resourceBundle.getText("ORGANIZER");
                case "CLIENT_MANAGER":
                    return resourceBundle.getText("CLIENT_MANAGER");
                case "PLANT_MANAGER":
					return resourceBundle.getText("PLANT_MANAGER");
				case "OPERATOR":
                    return resourceBundle.getText("OPERATOR");
                default:
                    return role;
            }
        },

        responsibleAreaDesc: function(area) {
            var resourceBundle = this.getView().getModel("i18n").getResourceBundle();
            switch (area) {
                case "COMPANY":
                    return resourceBundle.getText("COMPANY");
                case "CLIENT":
                    return resourceBundle.getText("CLIENT");
                case "PLANT":
					return resourceBundle.getText("PLANT");
                default:
                    return area;
            }
        }
	};

	return formatter;
});