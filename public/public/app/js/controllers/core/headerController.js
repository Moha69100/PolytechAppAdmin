app.controller('headerController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {
        $rootScope.isOpen = true;
        $scope.usingIE = false;
        if (browser() != "firefox") {
            $scope.usingIE = true;
        }
        $scope.resize = function () {
            $rootScope.isOpen = !$rootScope.isOpen;
        };

        var init = function () {
        };

        init();

    }]);