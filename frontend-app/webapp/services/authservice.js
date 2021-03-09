sap.ui.define([
    "workerapp/model/service",
    "workerapp/model/constant"
], function (Service, Constant) {
    "use strict";

    return {
        //STAFF
        updateUserGroup: function(person) {
            return Service.doAjax(Constant.SERVICE_URI + "/auth/group/"+ person.groupName + "/user/" + person.username, null, "PUT", true);
        },
        addClientManager: function(person) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/add-clientmanager",person, "POST", true);
        },
        addCompanyOwner: function(person) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/add-companyowner",person, "POST", true);
        },
        addOperator: function(person) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/add-operator",person, "POST", true);
        },
        addPlantManager: function(person) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/add-plantmanager",person, "POST", true);
        },
        getStaff: function() {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/users", null, "GET", true);
        },
        getStaffByUserId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/user/"+id, null, "GET", true);
        },
        updateStaff: function(person) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/update-user", person, "PUT", true);
        },

        getStaffRolesByUserId: function(id) {
            return Service.doAjax(Constant.baseUri() + "/auth/responsible-area/user/" + id, null, "GET", true);
        },

        addCompanyOwnerRole: function(data) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/companyowner-role", data, "POST", true);
        },

        addClientManagerRole: function(data) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/clientmanager-role", data, "POST", true);
        },

        addPlantManagerRole: function(data) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/plantmanager-role", data, "POST", true);
        },

        addOperatorRole: function(data) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/operator-role", data, "POST", true);
        },

        removeRole: function(id) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/remove-role/" + id, null, "DELETE", true);
        },

        removeUser: function(id) {
            return Service.doAjax(Constant.baseUri() + "/auth/keycloak/user/" + id, null, "DELETE", true);
        }

    }
});