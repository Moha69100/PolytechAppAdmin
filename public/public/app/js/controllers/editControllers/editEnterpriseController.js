app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location",
    function ($scope, $routeParams, enterpriseResource, $location) {
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
            $scope.$apply(function () {
                $location.path("/admin-enterprise");
            });

        };

        $scope.redirectEnterprise = function () {
            $location.path('#/admin-enterprise');
        };

        $scope.init();
    }]);


