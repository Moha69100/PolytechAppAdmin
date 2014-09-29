app.controller('eventsController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 'eventResource',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser, eventResource) {



        var init = function () {
            $scope.events = eventResource.listEvents();
        };

        $scope.editEvent = function(event) {
            $location.search('eventId', event.id).path('edit-event');
        }
        
        init();

    }]);