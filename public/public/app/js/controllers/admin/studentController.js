app.controller('studentController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', "studentResource", "$modal",
    function ($rootScope, $scope, $http, $timeout, $location, $window, studentResource, $modal)  {


        var init = function () {
            studentResource.listStudents(function (data) {
                $scope.students = data;
            });
        };

        $scope.importStudents = function () {
            // ouverture modale, avec le type à modifier, et un flag
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/students/importStudentForm.html',
                controller: 'importStudentModalController',
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
            $location.search('student', studentEdited.id).path('/edit-student');
        };

        init();

    }]);