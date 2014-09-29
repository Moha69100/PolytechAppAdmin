

app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location",
    function ($scope, $routeParams, enterpriseResource, $location) {
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
        $scope.remove = function (enterprise) {
            console.log(enterprise.enterpriseId);
            enterpriseResource.removeEnterprise({"id": enterprise.enterpriseId}, function (data) {
                console.log(data)
                $location.path('/edit-enterprise');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $scope.$apply(function () {
                $location.path("/admin-enterprise");
            });
        };

        $scope.rediretcEnterprise = function () {
            $location.path('#/admin-enterprise');
        };

        $scope.init();
    }]);

