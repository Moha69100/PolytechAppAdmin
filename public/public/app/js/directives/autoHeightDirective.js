'use strict';
app.directive('autoHeight', function () {

    return function ($scope, $element, $attributes) {

        function changeHeight() {
            var height = window.innerHeight - 85;
           $element.css({
                'height': height
            });
           
        }
        changeHeight();
        $(window).resize(changeHeight);

    };
});