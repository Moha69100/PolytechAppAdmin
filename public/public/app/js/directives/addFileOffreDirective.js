app.directive('addFileOffre', function($compile) {
	return {
		template : '<div ng-include="getContentUrl()"></div>',
		replace : true,
		restrict : "A",
		scope : true,
		link : function($scope, element, attr) {
			$scope.fileType = attr.fileType;
			$scope.onglet = attr.onglet;
			$scope.getContentUrl = function() {
				if ($scope.onglet == "desc")
					return 'app/partials/offre/addFileOffreDescTemplate.html';
				else
					return 'app/partials/offre/addFileOffreTemplate.html';
			};
		}
	};
});