sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        createStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/rest/stock/stockinfo", stock, "POST", true);
        },
        updateStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/rest/stock/stockinfo", stock, "PUT", true);
        },
        deleteStockInfoById: function(id) {
            return Service.doAjax("http://localhost:4000/rest/stock/stockinfo/" + id, null, "DELETE", true);
        },
        getStockInfoById: function(id){
            return Service.doAjax("http://localhost:4000/rest/stock/stockinfo/" + id, null, "GET", true);
        }

    }
});