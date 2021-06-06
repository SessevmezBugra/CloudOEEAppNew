sap.ui.define([
    "workerapp/model/service",
    "workerapp/model/constant"
], function (Service, Constant) {
    "use strict";
    
    return {
        //HIERARCHY
        getHierarchyHeaders: function(id){
            return Service.doAjax(Constant.SERVICE_URI + "/main-data/hierarchy-header",null,"GET",true);
        },
        createHierarchy: function(hierarchy) {
            return Service.doAjax(Constant.SERVICE_URI + "/main-data/hierarchy-header", hierarchy, "POST", true);
        },

        //NODES
        getGlobalNodes: function() {
            return Service.doAjax(Constant.SERVICE_URI + "/main-data/node", null, "GET", true);
        },

        //PLANT
        getPlantInfo: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/plant/"+id,null,"GET",true);
        },
        getPlants: function(){
            return Service.doAjax(Constant.baseUri() + "/main-data/plant",null,"GET",true);
        },
        createPlant: function(plant){
            return Service.doAjax(Constant.baseUri() + "/main-data/plant",plant,"POST",true);
        },
        getPlantByClientId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/main-data/plant/client/" + id, null, "GET", true);
        },
        deletePlantById: function(id) {
            return Service.doAjax(Constant.baseUri() + "/main-data/plant/" + id, null, "DELETE", true);
        },

        //WAREHOUSE
        //giriş yapan kullanıcının sorumlu olduğu depoları döner
        getWarehouses: function(){
            return Service.doAjax(Constant.baseUri() + "/main-data/warehouse",null,"GET",true);
        },
        createWarehouse: function(warehouse){
            return Service.doAjax(Constant.baseUri() + "/main-data/warehouse",warehouse,"POST",true);
        },
        updateWarehouse: function(warehouse){
            return Service.doAjax(Constant.baseUri() + "/main-data/warehouse",warehouse,"PUT",true);
        },
        getWarehousesByPlantId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/main-data/warehouse/plant/" + id, null, "GET", true);
        },
        deleteWarehouseById: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/warehouse/" + id, null,"DELETE",true);
        },

        //MATERIAL
        getMaterials: function(){
            return Service.doAjax(Constant.baseUri() + "/main-data/material",null,"GET",true);
        },
        createMaterial: function(material){
            return Service.doAjax(Constant.baseUri() + "/main-data/material",material,"POST",true);
        },
        updateMaterial: function(material){
            return Service.doAjax(Constant.baseUri() + "/main-data/material",material,"PUT",true);
        },
        getMaterialsByPlantId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/main-data/material/plant/" + id, null, "GET", true);
        },
        deleteMaterialById: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/material/" + id, null, "DELETE", true);
        },

        //REASON CODE
        getReasonCodesByPlantId: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/reason-code/plant/" + id, null, "GET", true);
        },
        createReasonCode: function(reasonCode){
            return Service.doAjax(Constant.baseUri() + "/main-data/reason-code", reasonCode, "POST", true);
            
        },
        updateReasonCode: function(reasonCode){
            return Service.doAjax(Constant.baseUri() + "/main-data/reason-code", reasonCode, "PUT", true);
            
        },
        deleteReasonCodeById: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/reason-code/" + id, null, "DELETE", true);
        },


        //QUALITY TYPE
        getQualityTypesByPlantId: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/quality-type/plant/" + id, null, "GET", true);
        },
        createQualityType: function(qualityType){
            return Service.doAjax(Constant.baseUri() + "/main-data/quality-type", qualityType, "POST", true);
        },
        updateQualityType: function(qualityType){
            return Service.doAjax(Constant.baseUri() + "/main-data/quality-type", qualityType, "PUT", true);
        },
        deleteQualityTypeById: function(id){
            return Service.doAjax(Constant.baseUri() + "/main-data/quality-type/" + id, null, "DELETE", true);
        },


    }
});