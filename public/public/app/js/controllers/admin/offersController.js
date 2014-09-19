app.controller('offersController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {



        var init = function () {
            $scope.offers = [{
                    id: 1,
                    name: "nom offre",
                    desc: "description description description description description description description\n\
 description description description description description description description description ",
                }];
        };

        init();

    }]);