'use strict';
app.controller('editEventController', ['$scope', '$modal', '$routeParams', 'eventResource', 'enterpriseResource', 'studentResource',
    function($scope, $modal, $routeParams, eventResource, enterpriseResource, studentResource) {

        $scope.enterprisesToRemove = [];
        $scope.studentsToRemove = [];

        
        $scope.enterprises = [];
        $scope.students = [];
        
        
        $scope.addEnterprise = function() {
            $scope.event.entreprisepresences.push($scope.attendingEnterprise);
            angular.forEach($scope.enterprises, function(val, key) {
                if (val.id == $scope.attendingEnterprise.id) {
                    $scope.enterprises.splice(key, 1);
                }
            });
        }

        $scope.addStudent = function() {
            $scope.event.etudiants.push($scope.attendingStudent);
            angular.forEach($scope.students, function(val, key) {
                if (val.id == $scope.attendingStudent.id) {
                    $scope.students.splice(key, 1);
                }
            });
        }

        $scope.prepareRemoveEnterprise = function(index) {
            if ($scope.enterprisesToRemove[index] == null) {
                $scope.enterprisesToRemove[index] = $scope.event.entreprisepresences[index];
            } else {
                $scope.enterprisesToRemove.splice(index, 1);
            }
            console.log($scope.enterprisesToRemove);
        }

        $scope.prepareRemoveStudent = function(index) {
            if ($scope.studentsToRemove[index] == null) {
                $scope.studentsToRemove[index] = $scope.event.etudiants[index];
            } else {
                $scope.studentsToRemove.splice(index, 1);
            }
            console.log($scope.studentsToRemove);
        }

        $scope.removeEnterprise = function() {
            angular.forEach($scope.enterprisesToRemove, function(val1, key1) {
                angular.forEach($scope.event.entreprisepresences, function(val2, key2) {
                    if (val1.id == val2.id) {
                        $scope.event.entreprisepresences.splice(key2, 1);
                        $scope.enterprises.push(val1);
                    }
                });
            });
            $scope.enterprisesToRemove = [];
        }

        $scope.removeStudent = function() {
            angular.forEach($scope.studentsToRemove, function(val1, key1) {
                angular.forEach($scope.event.etudiants, function(val2, key2) {
                    if (val1.id == val2.id) {
                        $scope.event.etudiants.splice(key2, 1);
                        $scope.students.push(val1);
                    }
                });
            });
        }

        $scope.save = function() {
            // TODO Enregistrer un event modifié
        };
        var init = function() {
            $scope.eventId = $routeParams.eventId;
            eventResource.getEvent({'id': $scope.eventId}, function(data) {
                console.log(data);
                $scope.event = data;
                $scope.event.entreprisepresences = [];
                $scope.event.etudiants = [];
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
        // --------------------- CODE DE LA MODAL ------------------------------
        $scope.openModal = function(size) {
            var myModal = $modal.open({
                templateUrl: 'planning.html',
                controller: 'planningController',
                size: 'size',
                windowClass: 'appAdminPlanningModal', 
                resolve: {
                    eventId: function() {
                        return $scope.eventId;
                    }
                }
            });
        }
        // ---------------------------------------------------------------------
    }]);