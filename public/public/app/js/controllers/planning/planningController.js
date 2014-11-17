app.controller('planningController', ['$rootScope', '$scope', 'meetingResource', '$modalInstance', 'eventId',
	function($rootScope, $scope, meetingResource, $modalInstance, eventId) {

		$scope.eventId = eventId;
		$scope.salles = [];
		$scope.meetings = [];

		var init = function() {
			meetingResource.listByEventId({
				'id_event': $scope.eventId
			}, function(data) {
				var preData = [],
					salles = [];
				console.log(data);
				// On parcours les infos ressources angular
				angular.forEach(data, function(result) {
					// On crée une liste des salles
					var search = _.find(salles, function(item) {
						return item.id == result.salle.id;
					});
					if (!search) {
						salles.push(result.salle);
					}
					// On ajoute salleId sur les objets meeting (facilite le groupBy)
					result.salleId = result.salle.id;
					// Calcul des dates debut / fin
					result.horaire = result.horaire.substring(0, result.horaire.length - 3);
					preData.push(result);
				});
				// On applique le groupBy
				$scope.salles = salles;
				$scope.meetings = _.groupBy(preData, 'salleId');
				console.log($scope.meetings);
				console.log($scope.salles);
			});
		};

		init();

		// Code pour le planning à afficher dans un événement
		$scope.cancel = function() {
			$modalInstance.dismiss('cancel');
		}
	}
]);