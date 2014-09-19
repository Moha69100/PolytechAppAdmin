app.controller('eventsController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {



        var init = function () {
            $scope.events = [{
                    id: 1,
                    name: "nom event",
                    desc: "description description description description description description description\n\
 description description description description description description description description ",
                }];
        };

        init();

    }]);