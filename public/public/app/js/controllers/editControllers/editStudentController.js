app.controller("editStudentController", ['$scope', 'studentResource', '$routeParams', '$location',
    function ($scope, studentResource, $routeParams, $location) {
        var init = function () {
            $scope.studentId = $routeParams.student;
            console.log($scope.studentId);
            studentResource.getStudent({"id": $scope.studentId}, function (data) {
                console.log(data);
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
            //appel ressource pour update
            $location.path('/admin-student');
        };

        $scope.removeStudent = function (student) {
            //appel ressource pour delete
            $location.path('/admin-student');
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $location.path('/admin-student');
        };
        
        init();
    }]);


