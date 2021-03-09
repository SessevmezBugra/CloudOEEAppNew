sap.ui.define([
    "workerapp/services/userservice"
], function (UserService) {
	"use strict";

	return {
		getUsername: function() {
            return UserService.getKeycloak().idTokenParsed.preferred_username;
        }
	};

});