sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        getCompanyInfo: function() {
            return Service.doAjax("http://localhost:4000/rest/maindata/companyinfo/8","", "GET", true);
        },
        getClientInfo: function(id){
            return Service.doAjax("http://localhost:4000/rest/maindata/clientinfo/"+id,null,"GET",true);
        },
        getPlantInfo: function(id){
            return Service.doAjax("http://localhost:4000/rest/maindata/plantinfo/"+id,null,"GET",true);
        }

    }
});