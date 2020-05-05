sap.ui.define([
	"./LocalStorageModel"
], function (LocalStorageModel) {
	"use strict";

	return {
		doAjax: function (path, content, type, async) {
            var promise = new Promise(function (resolve, reject) {
            var params = {
                headers:{},
                url: path,
                // dataType: "json",
                contentType: "application/json",
                // context: this,
                resolveWithFullResponse: true,
                cache: false
            };
            params.success = function (result, response, request) {
                resolve({
                    data: result,
                    response: response,
                    request: request
                });
            };
            params.error = function (error) {
                reject(error);
            };
            params["type"] = type || "POST";
            if (async === false) {
                params["async"] = async;
            }
            if (content) {
                params["data"] = JSON.stringify(content);
            }
            var localUserModel = new LocalStorageModel("localUserModel");
            if(localUserModel.getData()){
                params.headers.Authorization = localUserModel.getData().jwt;
            }
            jQuery.ajax(params);
        });
            return promise;
        },
	};

});