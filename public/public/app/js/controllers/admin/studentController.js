app.controller('studentController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', '$modal', 'browser',
    function($rootScope, $scope, $http, $timeout, $location, $window, $modal, browser) {



        var init = function() {
            $scope.students = [{
                    id: 1,
                    nom: "nom",
                    prenom: "prenom",
                    email: "test@eleve-polytech.gmail",
                    adresse: "12 rue de polytech"
                }]

        };

        $scope.addStudent = function() {

        };

        $scope.removeStudent = function(student) {

        };

        $scope.editStudent = function(studentEdited) {

            // ouverture modale, avec le type à modifier, et un flag
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/students/studentForm.html',
                controller: 'editStudentModalController',
                resolve: {
                    items: function() {
                        return {studentEdited: angular.copy(studentEdited), appel: "editStudent"};
                    }

                }
            });
            // gestion du retour de la modale : raffraichir page si tout va
            // bien , log sinon
            modalInstance.result.then(function(selectedType) {
                typeListe();
            }, function() {
                $log.info('Modal create type dismissed at: ' + new Date());
            });
        };

        init();

    }]);