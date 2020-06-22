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
        getCompanyInfo: function() {
            return Service.doAjax("http://localhost:4000/main-data/company/8","", "GET", true);
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

        //WAREHOUSE
        getWarehouses: function(){
            return Service.doAjax("http://localhost:4000/main-data/warehouse",null,"GET",true);
        },
        createWarehouse: function(warehouse){
            return Service.doAjax("http://localhost:4000/main-data/warehouse",warehouse,"POST",true);
        },

        //MATERIAL
        getMaterials: function(){
            return Service.doAjax("http://localhost:4000/main-data/material",null,"GET",true);
        },
        createMaterial: function(material){
            return Service.doAjax("http://localhost:4000/main-data/material",material,"POST",true);
        },

    }
});