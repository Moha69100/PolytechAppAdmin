

app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location",
    function ($scope, $routeParams, enterpriseResource, $location) {
        $scope.init = function () {
            if ($routeParams.enterprise) {

                $scope.enterpriseId = $routeParams.enterprise;
                enterpriseResource.getEnterprise({"id": $scope.enterpriseId}, function (data) {
                    $scope.enterprise = data;
                });
            } else {

            }
        };
        // 'feedback' serveur
        $scope.feedback = null;
        //     $scope.enterprise = items.enterpriseEdited;

        $scope.save = function () {
            var postData = $scope.enterprise;
            enterpriseResource.addEnterprise({}, postData);
        };
        
        $scope.update = function () {
            var postData = $scope.enterprise;
            enterpriseResource.updateEnterprise({}, postData);
        };


        $scope.removeEnterprise = function (enterprise) {
            enterpriseResource.removeEnterprise({"id": $scope.enterpriseId}, function (data) {
                $location.path('/admin-enterprise');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            delete ($scope.enterprise);
            delete ($scope.enterpriseId);
            $location.path('/admin-enterprise');
        };

        $scope.init();
    }]);

