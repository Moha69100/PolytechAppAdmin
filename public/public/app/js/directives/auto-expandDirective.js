app.directive('ngAutoExpand', function() {
	return {
		scope : false,
		restrict : 'A',
		link : function(scope, elem, attrs) {
			if (attrs.onglet) {
				elem.bind('mousedown', function($event) {
					setTimeout(function() {
						angular.forEach(scope.elements, function(value, key) {
							var element = value;
							$(element).height(0);
							var height = $(element)[0].scrollHeight;
							$(element).height(height);
						});
					}, 1000);
					/* FIXME
					 * necessite que l'elem soit visible pour que la directive
					 * fonctionne... => 1sec de délai pour être "certain" que
					 * lorsqu'on clique sur "vente", les inputs se redim.
					 * => commet detecter que l'elem est chargé ?
					 */

				});
			} else {
				elem.bind('keyup', function($event) {
					var element = $event.target;
					$(element).height(0);
					var height = $(element)[0].scrollHeight;
					$(element).height(height);
				});
				elem.bind('mousedown', function($event) {
					var element = $event.target;
					$(element).height(0);
					var height = $(element)[0].scrollHeight;
					$(element).height(height);
				});
				scope.$watch(attrs.ngModel,function(newVal,oldVal){
					if (!attrs.donneesLocales) {
						scope.elements.push(elem);
					}
					var element = elem;
					$(element).height(0);
					var height = $(element)[0].scrollHeight;
					$(element).height(height);
				});		
			}
		}
	};
});