'use strict';

app.controller('editEventController', ['$scope', '$routeParams', 'eventResource', 'enterpriseResource', 'studentResource',
    function($scope, $routeParams, eventResource, enterpriseResource, studentResource) {

        var eventId;

        $scope.enterprisesToRemove = [];

        // TODO supprimer ce tableau une fois le retour de la connexion à la base de données
        $scope.enterprises = [{
                id: 1,
                name: "VOLVO",
                representant: "Anthony RAMPON",
                phone: "01 01 01 01 01",
                adresse: "polyech !",
                role: "manager"
            }, {
                id: 2,
                name: "ORANGE",
                representant: "COHEN David",
                phone: "01 01 01 01 01",
                adresse: "polyech !",
                role: "manager"
            }, {
                id: 3,
                name: "GENERAL ELECTRICS",
                representant: "LONJON Louis",
                phone: "01 01 01 01 01",
                adresse: "polyech !",
                role: "manager"
            }];

        $scope.attendingEnterprise = null;

        $scope.attendingEnterprises = [];

        $scope.addEnterprise = function() {
            $scope.attendingEnterprises.push($scope.attendingEnterprise);

            angular.forEach($scope.enterprises, function(val, key) {
                if (val.id == $scope.attendingEnterprise.id) {
                    $scope.enterprises.splice(key, 1);
                }
            });
        }

        $scope.prepareRemove = function(index) {
            $scope.enterprisesToRemove.push($scope.attendingEnterprises[index]);
        }

        $scope.removeEnterprise = function() {
            angular.forEach($scope.enterprisesToRemove, function(val1, key1) {
                angular.forEach($scope.attendingEnterprises, function(val2, key2) {
                    if(val1.id == val2.id) {
                        $scope.attendingEnterprises.splice(key2, 1);
                        $scope.enterprises.push(val1);
                    }
                });
            });
            $scope.enterprisesToRemove = [];
        }

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