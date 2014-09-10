app.directive('addFileDemande', function($compile) {
	return {
		template : '<div ng-include="getContentUrl()"></div>',
		replace : true,
		restrict : "A",
		scope : true,
		link : function($scope, element, attr) {
			$scope.fileType = attr.fileType;
			$scope.getContentUrl = function() {
				return 'app/partials/demande/addFileDemandeTemplate.html';
			};
		}
	};
});