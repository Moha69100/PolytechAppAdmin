app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource",
    function ($scope, $routeParams, enterpriseResource) {
        $scope.init = function () {
            console.log("IN")
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
            $scope.$apply(function () {$location.path("/admin-enterprise");});
            
        };
        $scope.init();
    }]);


