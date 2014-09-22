app.controller('studentController', ['$scope', '$modal', '$location', 'studentInstance', function ($scope, $modal, $location, studentInstance) {


        var init = function () {
            $scope.students = [{
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
                    statCandidature: "Acceptée",
                    remarques: "rien a dire"
                }];

        };

        $scope.addStudent = function () {
            // ouverture modale, avec le type à modifier, et un flag
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/students/studentForm.html',
                controller: 'editStudentModalController',
                resolve: {
                    items: function () {
                        return {studentEdited: angular.copy($scope.students[2]),
                            appel: "addStudent"};
                    }

                }
            });
            // gestion du retour de la modale : raffraichir page si tout va
            // bien , log sinon
            modalInstance.result.then(function (modifiedStudent) {
                console.log(modifiedStudent);
                $scope.students[2] = modifiedStudent;
            }, function () {
                console.log("modal dismissed");
            });
        };



        $scope.editStudent = function (studentEdited) {
            studentInstance.setStudent(studentEdited);
            $location.url('/admin-student-modify');
        };

        init();

    }]);