'use strict';

app.controller('editEventController', ['$scope','$routeParams','eventResource', function($scope, $routeParams, eventResource){
    
    var eventId;
    
    $scope.event = {}
    var init = function() {
        eventId = $routeParams.eventId;
        eventResource.getEvent({'id':eventId}, function(data) {
            console.log(data);
            
            $scope.event = data;
        });
    }
    
    $scope.editAttendingCompanies = function() {
        console.log("Le bouton fonctionne");
    }
    
    $scope.editAttendingCandidate = function () {
        console.log("le bouton fonctionne");
    }
    
    init();
}]);