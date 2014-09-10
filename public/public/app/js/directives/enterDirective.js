app.directive('ngEnter', ['$animate', function($animate) {
	
	return function(scope, element, attrs) {
		
		element.bind("keydown keypress", function(event) {
			
			if (event.which === 13) {
				scope.$apply(function() {
					scope.$eval(attrs.ngEnter);
				});
				
				event.preventDefault();
				if (!(scope.searchKey && scope.searchKey.length > 1))
					$("#alert-search").addClass('in');
				else {
					$("#alert-search").removeClass('in');
				}

			}
		});
	};
}]);