app.controller("editStudentController", ['$scope', 'studentResource', '$routeParams',
    function ($scope, studentResource, $routeParams) {
        $scope.init = function () {
            $scope.studentId = $routeParams.enterprise;
            studentResource.getStudent({"id": $scope.studentId}, function (data) {
                console.log(data)
                $scope.student = data;
            });
        };
        
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.student = studentInstance.getStudent();
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
            //$modalInstance.close($scope.student);
        };

        $scope.removeStudent = function (student) {

            //$modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            //$modalInstance.dismiss();
        };
    }]);


