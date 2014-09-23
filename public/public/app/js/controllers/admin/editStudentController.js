app.controller("editStudentController", ['$scope', 'studentInstance',
    function ($scope, studentInstance) {
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
    }]).config(['$routeProvider', function ($routeProvider) {
        $routeProvider
    }]);


