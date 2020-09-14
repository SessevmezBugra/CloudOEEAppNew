sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        createStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stock-info", stock, "POST", true);
        },
        updateStockInfo: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stock-info", stock, "PUT", true);
        },
        // Varsa stoğun üzerine ekler yoksa oluşturur.
        addStock: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stock-info/add", stock, "PUT", true);
        },
        // Varsa stoktan çıkarma yapar. Yoksa hata döner.
        exctractStock: function(stock) {
            return Service.doAjax("http://localhost:4000/stock/stock-info/extract", stock, "PUT", true);
        },
        deleteStockInfoById: function(id) {
            return Service.doAjax("http://localhost:4000/stock/stock-info/" + id, null, "DELETE", true);
        },
        getStockInfoById: function(id){
            return Service.doAjax("http://localhost:4000/stock/stock-info/" + id, null, "GET", true);
        },
        getStockInfoByWarehouseId: function(id){
            return Service.doAjax("http://localhost:4000/stock/stock-info/warehouse/" + id, null, "GET", true);
        },
        getStocksByPlantId: function(id){
            return Service.doAjax("http://localhost:4000/stock/stock-info/plant/" + id, null, "GET", true);
        },
        getStockMovByWarehouseId: function(id){
            return Service.doAjax("http://localhost:4000/stock/stock-movement/warehouse/" + id, null, "GET", true);
        },
        getStockInfoByWarehouseIdAndMaterialId: function(warehouseId, materialId) {
           return Service.doAjax("http://localhost:4000/stock/stock-info/warehouse/" + warehouseId + "/material/" + materialId, null, "GET", true);
        }
    }
});