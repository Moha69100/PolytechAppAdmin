app.controller("editStudentController", ['$scope', 'studentResource', '$routeParams', '$location',
    function ($scope, studentResource, $routeParams, $location) {
        
        var init = function () {
            $scope.studentId = $routeParams.student;
            studentResource.getStudent({"id": $scope.studentId}, function (data) {
                $scope.student = data;
            });
        };

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.bacList = [
            {"name": "S"},
            {"name": "L"},
            {"name": "ES"}
        ];

        $scope.statCandidature = [
            {"name": "Acceptée"},
            {"name": "En cours"},
            {"name": "Rejetée"}
        ];

        $scope.save = function (student) {
            studentResource.updateStudent({"student": student}, function (data) {
                $location.path('/admin-student');
            });
        };

        $scope.removeStudent = function (student) {
            studentResource.removeStudent({"id": $scope.studentId}, function (data) {
                $location.path('/admin-student');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            delete ($scope.student);
            delete ($scope.studentId);
            $location.url('/admin-student');
        };
        init();
    }]);


