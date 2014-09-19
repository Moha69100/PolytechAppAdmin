app.controller("editEnterpriseModalController", ['$scope', '$modalInstance', 'items',
    function ($scope, $modalInstance, items) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.enterprise = items.enterpriseEdited;

        $scope.save = function (enterprise) {
            $modalInstance.close($scope.enterprise);
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


