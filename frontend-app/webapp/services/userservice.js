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
            this.keycloak = new Keycloak({
                url: 'http://arcloudfactories.com:8081/auth',
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
                silentCheckSsoRedirectUri: 'http://arcloudfactories.com' + '/silent-check-sso.html'
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