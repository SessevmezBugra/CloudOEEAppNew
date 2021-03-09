sap.ui.define([
    "sap/m/MessageBox",
], function (MessageBox) {
	"use strict";

	return {
		messageBox: function(type, message, callback) {
            MessageBox[type](message, {
				actions: [MessageBox.Action.OK, MessageBox.Action.CANCEL],
				emphasizedAction: MessageBox.Action.OK,
				onClose: function (sAction) {
                    if(sAction == "OK") {
                        callback(true);
                    }else{
                        callback(false);
                    }
				}
			});
        }
	};

});