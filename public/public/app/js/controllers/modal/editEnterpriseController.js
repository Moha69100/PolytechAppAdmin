app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource",
    function ($scope, $routeParams, enterpriseResource) {
        $scope.init = function () {
            $scope.enterpriseId = $routeParams.enterprise;
            enterpriseResource.getEnterprise({"id": $scope.enterpriseId}, function (data) {
                console.log(data)
                $scope.enterprise = data;
            });
        };
        // 'feedback' serveur
        $scope.feedback = null;
        //     $scope.enterprise = items.enterpriseEdited;

        $scope.save = function (enterprise) {
            //   $modalInstance.close($scope.enterprise);
        };
        $scope.removeEnterprise = function (enterprise) {

            // $modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            //  $modalInstance.dismiss();
        };
        $scope.init();
    }]);


