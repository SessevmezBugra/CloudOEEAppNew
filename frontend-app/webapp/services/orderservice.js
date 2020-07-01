sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        //ORDERINFO
        createOrder: function(order) {
            return Service.doAjax("http://localhost:4000/order/orderinfo",order, "POST", true);
        },

    }
});