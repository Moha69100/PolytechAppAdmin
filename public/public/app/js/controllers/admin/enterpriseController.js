app.controller('enterpriseController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', "enterpriseResource", "$modal",
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser, enterpriseResource, $modal) {



        var init = function () {
            enterpriseResource.listEnterprises(function (data) {
                $scope.enterprises = data;
                console.log($scope.enterprises)

            });
        };

        $scope.addEnterprise = function () {
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/enterprises/enterpriseForm.html',
                controller: 'editEnterpriseModalController',
                resolve: {
                    items: function () {
                        return{};
                    }
                }
            });

            modalInstance.result.then(function (modifiedEnterprise) {
                $scope.enterprises[modifiedEnterprise.id] = modifiedEnterprise;
            }, function () {
                console.log("modal dismissed");
            });
        };

        $scope.editEnterprise = function (enterpriseEdited) {
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/enterprises/enterpriseForm.html',
                controller: 'editEnterpriseModalController',
                resolve: {
                    items: function () {
                        return {enterpriseEdited: angular.copy(enterpriseEdited), appel: "editEnterprise"};
                    }
                }
            });

            modalInstance.result.then(function (modifiedEnterprise) {
                $scope.enterprises[modifiedEnterprise.id] = modifiedEnterprise;
            }, function () {
                console.log("modal dismissed");
            });
        };

        init();

    }]);