'use strict';

app.directive('canauxvente', function() {
	
	var directive = {};

	directive.restrict = 'A'; /* restrict this directive to elements */
	directive.scope = { 
				canauxvente: "="
			},
	
	directive.template = '<div  class="directive-canaux-div" ng-repeat="canal in canauxvente track by $index">' +
								'<div>{{canal.label}}</div>' + 
								'<img data-dr-auth-click="{ auth: \'OFFRE_STATUS_BROUILLON\' }" ng-show="canal.value != true" src="app/img/market/market-priority-0.png" style="cursor:pointer" ng-click="changeValue(canal)"/>' +
								'<img data-dr-auth-click="{ auth: \'OFFRE_STATUS_BROUILLON\' }" ng-show="canal.value == true" src="app/img/market/market-priority-2.png" style="cursor:pointer" ng-click="changeValue(canal)"/>' +
						 '</div>',
	
	directive.compile = function(element, attributes) {

		var linkFunction = function(scope, element, attributes) {

			scope.changeValue = function(canal) {

				if(canal.value != true) {
					canal.value = CONST_OFFRE_CANAL_VENTE_TRUE;
				}
				else {
					canal.value = CONST_OFFRE_CANAL_VENTE_FALSE;
				}
			};
			
		};

		
		
		return linkFunction;
	};

	return directive;
});