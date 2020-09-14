sap.ui.define([], function () {
    "use strict";

    return {

        deleteAssetBtnText: function (type) {
            var resourceBundle = this.getView().getModel("i18n").getResourceBundle();
            switch (type) {
                case "company":
                    return resourceBundle.getText("ASSETDETAIL_COMPANY_DELETE");
                case "client":
                    return resourceBundle.getText("ASSETDETAIL_CLIENT_DELETE");
                case "plant":
					return resourceBundle.getText("ASSETDETAIL_PLANT_DELETE");
                default:
                    return type;
            }
        },
        
        assetDetailTitle: function (type, data) {
            switch (type) {
                case "company":
                    return data.companyName;
                case "client":
                    return data.clientName;
                case "plant":
					return data.plantName;
                default:
                    return type;
            } 
        },

        updateQualityTypeVisiblity: function(qualityType) {
            if(qualityType != "SCRAP" && qualityType != "FIRST_QUALITY" && qualityType != "SECOND_QUALITY") {
                return true;
            }else {
                return false;
            }
        }
        
    };
});