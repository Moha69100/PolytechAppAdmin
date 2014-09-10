app.controller("menuController", [ '$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 
                                   function($rootScope, $scope, $http, $timeout, $location, $window, browser) {

	
	// Définition de la méthode d'initialisation
	$scope.init = function() {
		$scope.nbCarMin = true;
	};

	$scope.globalSearch = function() {
		if ($scope.searchKey) {
			
			$scope.nbCarMin = false;
			if ($scope.searchKey.length > 1) {
				$location.search('value', $scope.searchKey).path('/recherche');
				$scope.nbCarMin = true;
			}
		}
	};
	
	$scope.init();
}

]);
