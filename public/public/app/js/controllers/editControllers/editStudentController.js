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
            var postData = {
                student: $scope.student
            };
            console.log("in");
            studentResource.updateStudent({}, postData, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
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


