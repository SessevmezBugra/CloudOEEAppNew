sap.ui.define([
], function () {
	"use strict";

	return {
		baseUri: function () {
			return "https://service.arcloudfactories.com";
            // return (window.location.origin.includes("localhost") ? "http://localhost:4000" : "https://service.arcloudfactories.com")
        },
	};

});