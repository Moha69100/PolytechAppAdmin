'use strict';
app.directive('autoHeight', function() {

	return function($scope, $element, $attributes) {

		function changeHeight() {
			var height = window.innerHeight - 114;
			$element.css({
				'height' : height
			});
			$(".gridStyle").css({
				'height' : height - 51
			});
			$(".donneesLocalesModalBody").css({
				'height' : height - 101,
				"overflow" : "auto"
			});	
		}
		changeHeight();
		$(window).resize(changeHeight);

	};
});