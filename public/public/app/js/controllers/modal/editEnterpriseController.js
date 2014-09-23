app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource",
    function ($scope, $routeParams, enterpriseResource) {
        $scope.init = function () {
            $scope.enterpriseId = $routeParams.roomResource;
            roomResource.getRoom({"id": $scope.roomId}, function (data) {
                console.log(data)
                $scope.room = data;
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


