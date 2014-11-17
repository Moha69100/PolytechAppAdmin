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
					var horaireStart = result.horaire.split(':'),
						horaireEnd = result.duree.split(':');
					horaireStart = _.map(horaireStart, function(value) {
						return parseInt(value);
					});
					horaireEnd = _.map(horaireEnd, function(value) {
						return parseInt(value);
					});

					horaireEnd[0] = horaireStart[0] + Math.floor((horaireStart[1] + horaireEnd[1]) / 60);
					horaireEnd[1] = (horaireStart[1] + horaireEnd[1]) % 60;
					// Reformat to string
					var formatDigit = function(value) {
						return ("0" + value).slice(-2);
					};
					horaireStart = _.map(horaireStart, formatDigit);
					horaireEnd = _.map(horaireEnd, formatDigit);
					result.horaire = horaireStart[0] + ':' + horaireStart[1];
					result.horaireEnd = horaireEnd[0] + ':' + horaireEnd[1];
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