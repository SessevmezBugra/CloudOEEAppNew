sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        //COMPANY
        createCompany: function(company) {
            return Service.doAjax("http://localhost:4000/main-data/company",company, "POST", true);
        },
        getCompanies: function() {
            return Service.doAjax("http://localhost:4000/main-data/company","", "GET", true);
        },
        getCompanyInfo: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/company/" + id, null, "GET", true);
        },
        deleteCompanyById: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/company/" + id, null, "DELETE", true);
        },

        //CLIENT
        getClients: function() {
            return Service.doAjax("http://localhost:4000/main-data/client", null, "GET", true);
        },
        createClient: function(client) {
            return Service.doAjax("http://localhost:4000/main-data/client", client, "POST", true);
        },
        getClientInfo: function(id){
            return Service.doAjax("http://localhost:4000/main-data/client/"+id,null,"GET",true);
        },
        getClientInfoByCompanyId: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/client/company/" + id, null, "GET", true);
        },
        deleteClientById: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/client/" + id, null, "DELETE", true);
        },

        //PLANT
        getPlantInfo: function(id){
            return Service.doAjax("http://localhost:4000/main-data/plant/"+id,null,"GET",true);
        },
        getPlants: function(){
            return Service.doAjax("http://localhost:4000/main-data/plant",null,"GET",true);
        },
        createPlant: function(plant){
            return Service.doAjax("http://localhost:4000/main-data/plant",plant,"POST",true);
        },
        getPlantByClientId: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/plant/client/" + id, null, "GET", true);
        },
        deletePlantById: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/plant/" + id, null, "DELETE", true);
        },

        //WAREHOUSE
        //giriş yapan kullanıcının sorumlu olduğu depoları döner
        getWarehouses: function(){
            return Service.doAjax("http://localhost:4000/main-data/warehouse",null,"GET",true);
        },
        createWarehouse: function(warehouse){
            return Service.doAjax("http://localhost:4000/main-data/warehouse",warehouse,"POST",true);
        },
        updateWarehouse: function(warehouse){
            return Service.doAjax("http://localhost:4000/main-data/warehouse",warehouse,"PUT",true);
        },
        getWarehousesByPlantId: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/warehouse/plant/" + id, null, "GET", true);
        },
        deleteWarehouseById: function(id){
            return Service.doAjax("http://localhost:4000/main-data/warehouse/" + id, null,"DELETE",true);
        },

        //MATERIAL
        getMaterials: function(){
            return Service.doAjax("http://localhost:4000/main-data/material",null,"GET",true);
        },
        createMaterial: function(material){
            return Service.doAjax("http://localhost:4000/main-data/material",material,"POST",true);
        },
        updateMaterial: function(material){
            return Service.doAjax("http://localhost:4000/main-data/material",material,"PUT",true);
        },
        getMaterialsByPlantId: function(id) {
            return Service.doAjax("http://localhost:4000/main-data/material/plant/" + id, null, "GET", true);
        },
        deleteMaterialById: function(id){
            return Service.doAjax("http://localhost:4000/main-data/material/" + id, null, "DELETE", true);
        },

        //REASON CODE
        getReasonCodesByPlantId: function(id){
            return Service.doAjax("http://localhost:4000/main-data/reason-code/plant/" + id, null, "GET", true);
        },
        createReasonCode: function(reasonCode){
            return Service.doAjax("http://localhost:4000/main-data/reason-code", reasonCode, "POST", true);
            
        },
        updateReasonCode: function(reasonCode){
            return Service.doAjax("http://localhost:4000/main-data/reason-code", reasonCode, "PUT", true);
            
        },
        deleteReasonCodeById: function(id){
            return Service.doAjax("http://localhost:4000/main-data/reason-code/" + id, null, "DELETE", true);
        },


        //QUALITY TYPE
        getQualityTypesByPlantId: function(id){
            return Service.doAjax("http://localhost:4000/main-data/quality-type/plant/" + id, null, "GET", true);
        },
        createQualityType: function(qualityType){
            return Service.doAjax("http://localhost:4000/main-data/quality-type", qualityType, "POST", true);
        },
        updateQualityType: function(qualityType){
            return Service.doAjax("http://localhost:4000/main-data/quality-type", qualityType, "PUT", true);
        },
        deleteQualityTypeById: function(id){
            return Service.doAjax("http://localhost:4000/main-data/quality-type/" + id, null, "DELETE", true);
        },


    }
});