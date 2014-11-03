

app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location",
    function ($scope, $routeParams, enterpriseResource, $location) {
        $scope.init = function () {
            if ($routeParams.enterprise) {

                $scope.enterpriseId = $routeParams.enterprise;
                enterpriseResource.getEnterprise({"id": $scope.enterpriseId}, function (data) {
                    console.log(data)
                    $scope.enterprise = data;
                });
            } else {
                
            }
        };
        // 'feedback' serveur
        $scope.feedback = null;
        //     $scope.enterprise = items.enterpriseEdited;

        $scope.save = function () {
            var postData = {
                enterprise: $scope.enterprise
            };
            console.log("IN")
            enterpriseResource.addEnterprise(postData, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };
        
        
        $scope.removeEnterprise = function(enterprise) {
            console.log($scope.enterpriseId);
            enterpriseResource.removeEnterprise({"id": $scope.enterpriseId}, function (data) {
                console.log(data)
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

