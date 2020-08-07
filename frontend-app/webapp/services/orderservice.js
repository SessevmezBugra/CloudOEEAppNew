sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        //ORDERINFO
        createOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/order-info",order, "POST", true);
        },

        getOrdersByLoggedUser: function() {
            return Service.doAjax("http://localhost:4000/order/order-info",null, "GET", true);
        },

        getProducedMaterialByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/order/ordered-material/order/" + id, null, "GET", true); 
        },

        getConsumptionStockByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/order/consumption-material/order/" + id, null, "GET", true); 
        },

        startOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/order-info/start-order", order, "PUT", true); 
        },

        holdOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/order-info/hold-order", order, "PUT", true); 
        },

        resumeOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/order-info/resume-order", order, "PUT", true); 
        },

        completeOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/order-info/complete-order", order, "PUT", true); 
        },

        getActiveOrders: function() {
            return Service.doAjax("http://localhost:4000/order/order-info/active-order", null, "GET", true); 
        },

        getOrderInfoById: function(orderId) {
            return Service.doAjax("http://localhost:4000/order/order-info/" + orderId, null, "GET", true); 
        },
    }
});