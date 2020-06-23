sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        createStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stockinfo", stock, "POST", true);
        },
        updateStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stockinfo", stock, "PUT", true);
        },
        // Varsa stoğun üzerine ekler yoksa oluşturur.
        addStock: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stockinfo/add", stock, "PUT", true);
        },
        // Varsa stoktan çıkarma yapar. Yoksa hata döner.
        exctractStock: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stockinfo/extract", stock, "PUT", true);
        },
        deleteStockInfoById: function(id) {
            return Service.doAjax("http://localhost:4000/stock/stockinfo/" + id, null, "DELETE", true);
        },
        getStockInfoById: function(id){
            return Service.doAjax("http://localhost:4000/stock/stockinfo/" + id, null, "GET", true);
        },
        getStockInfoByWarehouseId: function(id){
            return Service.doAjax("http://localhost:4000/stock/stockinfo/warehouse/" + id, null, "GET", true);
        },

    }
});