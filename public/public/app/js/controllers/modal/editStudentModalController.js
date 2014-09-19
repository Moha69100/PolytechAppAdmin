app.controller("editStudentModalController", ['$scope', '$modalInstance', 'items',
    function ($scope, $modalInstance, items) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.student = items.studentEdited;

        $scope.save = function (student) {
            $scope.selected = {
                item: $scope.student
            };
            $modalInstance.close($scope.selected);
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $modalInstance.dismiss("test");
        };
    }]);


