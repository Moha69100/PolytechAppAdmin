app.controller("editStudentModalController", ['$scope', '$modalInstance', 'items',
    function ($scope, $modalInstance, items) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.student = items.studentEdited;

        $scope.save = function (student) {
            $modalInstance.close($scope.student);            
        };
        
        $scope.removeStudent = function (student) {
            
            $modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }]);


