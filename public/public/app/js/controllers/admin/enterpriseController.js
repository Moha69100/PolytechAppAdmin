app.controller('enterpriseController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', "enterpriseResource", "$modal",
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser, enterpriseResource, $modal) {

        var init = function () {
            enterpriseResource.listEnterprises(function (data) {
                $scope.enterprises = data;
            });
        };

        $scope.addEnterprise = function () {
            $location.path('/edit-enterprise');
        };

        $scope.editEnterprise = function (enterpriseEdited) {
            $location.search('enterprise', enterpriseEdited.id).path('/edit-enterprise');
        };


        init();

    }]);