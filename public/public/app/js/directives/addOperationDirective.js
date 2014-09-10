app.directive('addOperation', function($compile) {
	return {
		template : '<div ng-include="getContentUrl()"></div>',
		replace : true,
		restrict : "A",
		link : function(scope, element, attr) {
			scope.getContentUrl = function() {
				if (attr.template == "vente") {
					return 'app/partials/offre/operationVenteTemplate.html';
				} else if (attr.template == "commande") {
					return 'app/partials/offre/operationCommandeTemplate.html';
				}
			};
		}
	};
});