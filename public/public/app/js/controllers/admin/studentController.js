app.controller('studentController', ['$scope', '$modal', function ($scope, $modal) {


        var init = function () {
            $scope.students = {1:{
                    id: 1,
                    nom: "TOTO",
                    prenom: "Test",
                    email: "test@eleve-polytech.gmail",
                    adresse: "12 rue de polytech",
                    dateNaiss: "12/12/1994",
                    codePost: "69003",
                    ville: "Lyon",
                    pays: "France",
                    tel: "0606060606",
                    bac: "S",
                    optBac: "SI",
                    mentionBac: "AB",
                    anneeBac: "2000",
                    diplomeEu: "Licence langue",
                    diplomeAavoir: "DUT Info",
                    etablissement: "IUT A Lyon",
                    statCandidature: "Accepté",
                    remarques: "rien a dire",
                }};

        };

        $scope.addStudent = function () {
            // ouverture modale, avec le type à modifier, et un flag
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/students/studentForm.html',
                controller: 'editStudentModalController',
                resolve: {
                    items: function () {
                        return {};
                    }

                }
            });
            // gestion du retour de la modale : raffraichir page si tout va
            // bien , log sinon
            modalInstance.result.then(function (modifiedStudent) {
                $scope.students[modifiedStudent.id] = modifiedStudent;
            }, function () {
               console.log("modal dismissed");
            });
        };

        

        $scope.editStudent = function (studentEdited) {

            // ouverture modale, avec le type à modifier, et un flag
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/students/studentForm.html',
                controller: 'editStudentModalController',
                resolve: {
                    items: function () {
                        return {studentEdited: angular.copy(studentEdited), appel: "editStudent"};
                    }

                }
            });
            // gestion du retour de la modale : raffraichir page si tout va
            // bien , log sinon
            modalInstance.result.then(function (modifiedStudent) {
                $scope.students[modifiedStudent.id] = modifiedStudent;
            }, function () {
               console.log("modal dismissed");
            });
        };

        init();

    }]);