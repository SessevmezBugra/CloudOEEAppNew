sap.ui.define([
], function () {
	"use strict";

	return {
		SERVICE_URI: (window.location.origin.includes("localhost") ? "http://localhost:4000" : "https://service.arcloudfactories.com"),
		
		KEYCLOAK_URI: (window.location.origin.includes("localhost") ? "http://localhost:8081" : "https://service.arcloudfactories.com"),
		
		COMPANY_OWNER: "COMPANY_OWNER",

		STAFF: "STAFF"
	};

});