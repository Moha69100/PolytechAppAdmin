app.controller("editStudentModalController", ['$scope', '$modalInstance', 'items', '$log',
    function($scope, $modalInstance, items, $log) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.student = items.studentEdited;

        $scope.save = function (student){
            $scope.selected = {
                item: $scope.student
            };
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };
    }]);


