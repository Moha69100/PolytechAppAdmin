'use strict';
app.controller('editEventController', ['$scope', '$modal', '$routeParams', 'eventResource', 'enterpriseResource', 'studentResource', '$location', "$rootScope",
    function ($scope, $modal, $routeParams, eventResource, enterpriseResource, studentResource, $location, $rootScope) {

        $scope.enterprisesToRemove = [];
        $scope.studentsToRemove = [];


        $scope.enterprises = [];
        $scope.students = [];


        $scope.addEnterprise = function () {
            var ent = $scope.attendingEnterprise;
            var entreprisePresente = {};
            entreprisePresente.salle = {"id":1,"libelle":"Salle 20","localisation":"Rez de chaussé","capacite":24};
            entreprisePresente.voeuxEntreprise = [
                {
                    etudiantevenement:$scope.event.etudiantpresents[0]
                }
            ];
            entreprisePresente.presence = false;
            entreprisePresente.entreprise = ent;
            entreprisePresente.dureeentretien = "00:30"
            
            $scope.event.entreprisepresences.push(entreprisePresente);
            angular.forEach($scope.enterprises, function (val, key) {
                if (val.id == ent.id) {
                    $scope.enterprises.splice(key, 1);
                }
            });
        };

        $scope.addStudent = function () {
            var etu = $scope.attendingStudent;
            var etudiantPresent = {
                "etudiant":etu,
                "voeuxEtudiant":[]
            };
            
            $scope.event.etudiantpresents.push(etudiantPresent);
            angular.forEach($scope.students, function (val, key) {
                if (val.id == etu.id) {
                    $scope.students.splice(key, 1);
                }
            });
        };

        $scope.prepareRemoveEnterprise = function (index) {
            if ($scope.enterprisesToRemove[index] == null) {
                $scope.enterprisesToRemove[index] = $scope.event.entreprisepresences[index];
            } else {
                $scope.enterprisesToRemove.splice(index, 1);
            }
            console.log($scope.enterprisesToRemove);
        };

        $scope.prepareRemoveStudent = function (index) {
            if ($scope.studentsToRemove[index] == null) {
                $scope.studentsToRemove[index] = $scope.event.etudiantpresents[index];
            } else {
                $scope.studentsToRemove.splice(index, 1);
            }
            console.log($scope.studentsToRemove);
        };

        $scope.removeEnterprise = function () {
            angular.forEach($scope.enterprisesToRemove, function (val1, key1) {
                angular.forEach($scope.event.entreprisepresences, function (val2, key2) {
                    if (val1.id == val2.id) {
                        $scope.event.entreprisepresences.splice(key2, 1);
                        $scope.enterprises.push(val1);
                    }
                });
            });
            $scope.enterprisesToRemove = [];
        };

        $scope.removeStudent = function () {
            angular.forEach($scope.studentsToRemove, function (val1, key1) {
                angular.forEach($scope.event.etudiantpresents, function (val2, key2) {
                    if (val1.id == val2.id) {
                        $scope.event.etudiantpresents.splice(key2, 1);
                        $scope.students.push(val1);
                    }
                });
            });
        };

        $scope.save = function () {
            console.log($scope.event)
            if ($scope.eventId != null) {
                update();
            } else {
                create();
            }
        };

        var update = function () {
            eventResource.updateEvent({}, $scope.event, function () {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Evênement mis à jour");

            });
        };

        var create = function () {
            eventResource.createEvent({}, $scope.event, function () {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Evênement enregistré");

            });
        };

        var init = function () {
            $scope.eventId = $routeParams.eventId;
            if ($scope.eventId != null) {
                eventResource.getEvent({'id': $scope.eventId}, function (data) {
                    console.log(data);
                    $scope.event = data;
                    //$scope.event.entreprisepresences = [];
                    //$scope.event.etudiantpresents = [];
                });
                enterpriseResource.listEnterprises(function (data) {
                    $scope.enterprises = data;
                });
                studentResource.listStudents(function (data) {
                    $scope.students = data;
                });
            }
        };


        init();
        // --------------------- CODE DE LA MODAL ------------------------------
        $scope.openModal = function (size) {
            var myModal = $modal.open({
                templateUrl: 'planning.html',
                controller: 'planningController',
                size: 'size',
                windowClass: 'appAdminPlanningModal',
                resolve: {
                    eventId: function () {
                        return $scope.eventId;
                    }
                }
            });
        };
        // ---------------------------------------------------------------------

        $scope.cancel = function () {
            delete ($scope.event);
            delete ($scope.eventId);
            $location.url($location.path());
            $location.url('/admin-events');
        };
        $scope.removeEvent = function () {
            eventResource.deleteEvenement({'id': $routeParams.eventId}, function (data) {
                $scope.cancel();
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Evênement supprimé");
            });
        };
        $scope.generatePlanning = function () {
            eventResource.generatePlanning({'id': $routeParams.eventId}, function (data) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Planning généré avec succès");
            });
        };
    }]);