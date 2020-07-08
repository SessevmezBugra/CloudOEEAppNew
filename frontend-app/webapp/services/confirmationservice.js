sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        getProdRunByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/confirmation/prodrunhdr/order/" + id, null, "GET", true); 
        }

    }
});