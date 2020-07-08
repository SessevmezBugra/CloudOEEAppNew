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

        getConsumptionMaterialByOrderId: function(id) {
            return Service.doAjax("http://localhost:4000/order/consumptionmaterial/order/" + id, null, "GET", true); 
        }

    }
});