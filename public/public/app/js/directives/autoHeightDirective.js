'use strict';
app.directive('autoHeight', function () {

    return function ($scope, $element, $attributes) {

        function changeHeight() {
            var height = window.innerHeight - 150;
            $element.css({
                'height': height
            });
            /*	$(".gridStyle").css({
             'height' : height - 51
             });
             */
        }
        changeHeight();
        $(window).resize(changeHeight);

    };
});