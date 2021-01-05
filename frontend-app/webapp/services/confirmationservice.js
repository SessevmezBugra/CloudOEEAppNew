sap.ui.define([
    "workerapp/model/service",
    "workerapp/model/constant"
], function (Service, Constant) {
    "use strict";
    
    return {

        getProdRunByOrderId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/confirmation/prodrunhdr/order/" + id, null, "GET", true); 
        },

        reportProdRunData: function(orderId, prodRunDatas) {
            return Service.doAjax(Constant.baseUri() + "/confirmation/prodrundata/order/" + orderId, prodRunDatas, "POST", true); 
        },

        reportConsumption: function(orderId, consumptionDatas) {
            return Service.doAjax(Constant.baseUri() + "/confirmation/consumptioninfo/order/" + orderId, consumptionDatas, "POST", true); 
        },

        getProdRunDataByRunId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/confirmation/prodrundata/run-id/" + id, null, "GET", true); 
        },

        getConsumptionDataByRunId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/confirmation/consumptioninfo/run-id/" + id, null, "GET", true); 
        }

    }
});