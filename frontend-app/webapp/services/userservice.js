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
        }

    }
});