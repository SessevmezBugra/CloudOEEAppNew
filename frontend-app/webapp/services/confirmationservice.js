sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        getProdRunByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/confirmation/prodrunhdr/order/" + id, null, "GET", true); 
        },

        reportProdRunData: function(orderId, prodRunDatas) {
            return Service.doAjax("http://localhost:4000/confirmation/prodrundata/order/" + orderId, prodRunDatas, "POST", true); 
        },

        reportConsumption: function(orderId, consumptionDatas) {
            return Service.doAjax("http://localhost:4000/confirmation/consumptioninfo/order/" + orderId, consumptionDatas, "POST", true); 
        },

        getProdRunDataByRunId: function(id) {
            return Service.doAjax("http://localhost:4000/confirmation/prodrundata/run-id/" + id, null, "GET", true); 
        },

        getConsumptionDataByRunId: function(id) {
            return Service.doAjax("http://localhost:4000/confirmation/consumptioninfo/run-id/" + id, null, "GET", true); 
        }

    }
});