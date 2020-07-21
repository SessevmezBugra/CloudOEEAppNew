sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {
        //STAFF
        addClientManager: function(person) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-clientmanager",person, "POST", true);
        },
        addCompanyOwner: function(person) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-companyowner",person, "POST", true);
        },
        addOperator: function(person) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-operator",person, "POST", true);
        },
        addPlantManager: function(person) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/add-plantmanager",person, "POST", true);
        },
        getStaff: function() {
            return Service.doAjax("http://localhost:4000/auth/keycloak/users", null, "GET", true);
        },
        getStaffByUserId: function(id) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/user/"+id, null, "GET", true);
        },
        updateStaff: function(person) {
            return Service.doAjax("http://localhost:4000/auth/keycloak/update-user", person, "PUT", true);
        }

    }
});