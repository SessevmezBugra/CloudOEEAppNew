sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        //ORDERINFO
        createOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo",order, "POST", true);
        },

        getOrdersByLoggedUser: function() {
            return Service.doAjax("http://localhost:4000/order/orderinfo",null, "GET", true);
        },

        getProducedMaterialByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/order/orderedmaterial/order/" + id, null, "GET", true); 
        },

        getConsumptionStockByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/order/consumptionmaterial/order/" + id, null, "GET", true); 
        },

        startOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo/start-order", order, "PUT", true); 
        },

        holdOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo/hold-order", order, "PUT", true); 
        },

        resumeOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo/resume-order", order, "PUT", true); 
        },

        completeOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo/complete-order", order, "PUT", true); 
        },

        getActiveOrders: function() {
            return Service.doAjax("http://localhost:4000/order/orderinfo/active-order", null, "GET", true); 
        },

        getOrderInfoById: function(orderId) {
            return Service.doAjax("http://localhost:4000/order/orderinfo/" + orderId, null, "GET", true); 
        },
    }
});