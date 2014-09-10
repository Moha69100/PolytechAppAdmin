app.controller('userController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {



        var init = function () {
            $scope.users = [{
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }, {
                    id: 1,
                    name: "test",
                    representant: "test test"
                }]
        };

        init();

    }]);