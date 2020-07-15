sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {
        //STAFF
        addClientManager: function(order) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-clientmanager",order, "POST", true);
        },
        addCompanyOwner: function(order) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-companyowner",order, "POST", true);
        },
        addOperator: function(order) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-operator",order, "POST", true);
        },
        addPlantManager: function(order) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-plantmanager",order, "POST", true);
        },
        getStaff: function() {
            return Service.doAjax("http://localhost:4000/auth/keycloak/users", null, "GET", true);
        },
        getStaffByUserId: function(id) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/user/"+id, null, "GET", true);
        }

    }
});