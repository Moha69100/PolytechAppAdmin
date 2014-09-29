'use strict';
app.directive('autoHeight', function () {

    return function ($scope, $element, $attributes) {

        function changeHeight() {
            var height = window.innerHeight - 60;
            $element.css({
                'height': height
            });
            $(".modal-body").css({
                'height': height - 150,
                "overflow-y": "auto"
            });
        }
        changeHeight();
        $(window).resize(changeHeight);

    };
});