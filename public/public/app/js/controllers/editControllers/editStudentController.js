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
            studentResource.updateStudent({"id": $scope.studentId}, function (data) {
                console.log(data);
                $location.path('/edit-student');
            });
        };

        $scope.removeStudent = function (student) {            
            studentResource.removeStudent({"id": $scope.studentId}, function (data) {
                console.log(data);
                $location.path('/admin-student');
            });            
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $scope.student = null;
            $scope.studentId = null;
            $location.url('/admin-student');
        };
        
        init();
    }]);


