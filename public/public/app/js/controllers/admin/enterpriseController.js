app.controller('enterpriseController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {



        var init = function () {
            $scope.enterprises = [{
                    id: 1,
                    name: "test",
                    representant: "test test",
                    phone: "01 01 01 01 01",
                    adresse: "polyech !",
                    role: "manager"
                }];
        };

        init();

    }]);