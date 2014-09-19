app.controller("editEnterpriseModalController", ['$scope', '$modalInstance', 'items',
    function ($scope, $modalInstance, items) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.student = items.enterpriseEdited;

        $scope.save = function (enterprise) {
            $scope.selected = {
                item: $scope.enterprise
            };
            $modalInstance.close($scope.selected);
        };
        
        $scope.removeEnterprise = function (enterprise) {
            
            $modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }]);


