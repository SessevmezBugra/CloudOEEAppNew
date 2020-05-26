sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";

    return {

        createOwnerUser: function(user) {
            return Service.doAjax("http://localhost:4000/rest/auth/user/registerowner", user, "POST", true);
        },
        createWorkerUser: function(user) {
            return Service.doAjax("http://localhost:4000/rest/auth/user/registerworker", user, "POST", true);
        },
        login: function(user) {
            return Service.doAjax("http://localhost:4000/rest/auth", user, "POST", true);
        },
        validateToken: function(){
            return Service.doAjax("http://localhost:4000/token/rest/validatetoken", null, "POST", true);
        }

    }
});