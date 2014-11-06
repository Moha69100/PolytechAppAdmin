'use strict';

app.controller('editEventController', ['$scope', '$routeParams', 'eventResource', 'enterpriseResource', 'studentResource', 
    function($scope, $routeParams, eventResource, enterpriseResource, studentResource) {

        var eventId;

        $scope.event = {}
        var init = function() {
            eventId = $routeParams.eventId;
            eventResource.getEvent({'id': eventId}, function(data) {
                console.log(data);

                $scope.event = data;
            });

            enterpriseResource.listEnterprises(function(data) {
                $scope.enterprises = data;
            });

            studentResource.listStudents(function(data) {
                $scope.students = data;
            });
        }

        $scope.editAttendingCompanies = function() {
            console.log("Le bouton fonctionne");
        }

        $scope.editAttendingCandidate = function() {
            console.log("le bouton fonctionne");
        }

        init();
    }]);