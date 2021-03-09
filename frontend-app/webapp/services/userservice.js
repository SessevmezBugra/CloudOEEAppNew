sap.ui.define([
    "workerapp/model/service",
    "workerapp/model/constant"
], function (Service, Constant) {
    "use strict";
    return {

        getKeycloak: function () {
            if (this.keycloak) {
                return this.keycloak;
            }
            // var KEYCLOAK_URI = "https://auth.arcloudfactories.com/auth";
            var KEYCLOAK_URI = window.location.origin.includes("localhost") ? "http://localhost:8080/auth" : "https://auth.arcloudfactories.com/auth";
            this.keycloak = new Keycloak({
                url: KEYCLOAK_URI,
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
                onLoad: 'check-sso',
                silentCheckSsoRedirectUri: window.location.origin + '/silent-check-sso.html'
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