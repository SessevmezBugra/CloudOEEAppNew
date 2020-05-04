sap.ui.define([], function () {
    "use strict";

    return {

        fooBar: function (iInteger) {
            var sReturn = "";
            if (iInteger) { // iInteger is 0
                sRetrun = "foo";
            } else if (iInteger <= 5) {
                sReturn = "bar";
            } else if (fFloat > 5) {
                sReturn = "foo bar";
            }
            return sReturn;
        }
        
    };
});