sap.ui.define([
    "workerapp/model/service",
    "workerapp/model/constant"
], function (Service, Constant) {
    "use strict";
    
    return {

        //ORDERINFO
        createOrder: function(order) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info",order, "POST", true);
        },

        getOrdersByLoggedUser: function() {
            return Service.doAjax(Constant.baseUri() + "/order/order-info",null, "GET", true);
        },

        getProducedMaterialByOrderId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/order/ordered-material/order/" + id, null, "GET", true); 
        },

        getConsumptionStockByOrderId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/order/consumption-material/order/" + id, null, "GET", true); 
        },

        getConsumptionStockByOrderIdWithoutWarehouseInfo: function(id) {
            return Service.doAjax(Constant.baseUri() + "/order/consumption-material/without-warehouse-info/order/" + id, null, "GET", true); 
        },

        startOrder: function(order) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/start-order", order, "PUT", true); 
        },

        holdOrder: function(order) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/hold-order", order, "PUT", true); 
        },

        resumeOrder: function(order) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/resume-order", order, "PUT", true); 
        },

        completeOrder: function(order) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/complete-order", order, "PUT", true); 
        },

        getActiveOrders: function() {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/active-order", null, "GET", true); 
        },

        getOrderInfoById: function(orderId) {
            return Service.doAjax(Constant.baseUri() + "/order/order-info/" + orderId, null, "GET", true); 
        },
    }
});