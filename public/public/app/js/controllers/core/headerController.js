app.controller('headerController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 
                                    function($rootScope, $scope, $http, $timeout, $location, $window, browser) {
	
	$scope.usingIE = false;
	if (browser() != "firefox") {
		$scope.usingIE = true;
	}


	var init = function() {
	};

	init();

}]);