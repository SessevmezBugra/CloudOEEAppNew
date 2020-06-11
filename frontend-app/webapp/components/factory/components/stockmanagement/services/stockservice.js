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
        },
        getStockInfoByWarehouseId: function(id){
            return Service.doAjax("http://localhost:4000/rest/stock/stockinfo/warehouse/" + id, null, "GET", true);
        },

        //Maindata Servisleri
        getWarehousesByPlantId: function(id) {
            return Service.doAjax("http://localhost:4000/rest/maindata/warehouseinfo/plant/" + id, null, "GET", true);
        },
        getMaterialsByPlantId: function(id) {
            return Service.doAjax("http://localhost:4000/rest/maindata/materialinfo/plant/" + id, null, "GET", true);
        },
        getPlantByClientId: function(id) {
            return Service.doAjax("http://localhost:4000/rest/maindata/plantinfo/client/" + id, null, "GET", true);
        },
        getClientInfoByCompanyId: function(id) {
            return Service.doAjax("http://localhost:4000/rest/maindata/clientinfo/company/" + id, null, "GET", true);
        },
        getCompanyInfoById: function(id) {
            return Service.doAjax("http://localhost:4000/rest/maindata/companyinfo/" + id, null, "GET", true);
        }
    }
});