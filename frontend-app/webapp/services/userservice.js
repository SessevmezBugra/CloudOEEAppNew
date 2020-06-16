sap.ui.define([
    "workerapp/model/service"
], function (Service) {
    "use strict";
    return {

        getKeycloak: function () {
            if (this.keycloak) {
                return this.keycloak;
            }
            this.keycloak = new Keycloak({
                url: 'http://keycloak.bulutfabrikasi.eu.ngrok.io/auth',
                realm: 'cloudoeeapp',
                clientId: 'ui-app'
            });
            return this.keycloak;
        },

        initLoginRequired: function () {
            return this.getKeycloak().init({
                onLoad: 'login-required'
            });
        },

        initCheckSSO: function () {
            return this.getKeycloak().init({
                onLoad: 'check-sso'
            });
        },

        login: function () {
            return this.getKeycloak().login();
        },

        logout: function () {
            return this.getKeycloak().logout();
        },

        createOwnerUser: function (user) {
            return Service.doAjax("http://localhost:4000/rest/auth/user/registerowner", user, "POST", true);
        },
        createWorkerUser: function (user) {
            return Service.doAjax("http://localhost:4000/rest/auth/user/registerworker", user, "POST", true);
        },
        // login: function (user) {
        //     return Service.doAjax("http://localhost:4000/rest/auth", user, "POST", true);
        // },
        validateToken: function () {
            return Service.doAjax("http://localhost:4000/token/rest/validatetoken", null, "POST", true);
        }

    }
});