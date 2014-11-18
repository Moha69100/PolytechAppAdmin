app.controller('eventsController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 'eventResource',
    function($rootScope, $scope, $http, $timeout, $location, $window, browser, eventResource) {

        console.log("plop")


        var init = function() {
            eventResource.listEvents(function(data) {
                $scope.events = data;
            });
        };

        $scope.editEvent = function(event) {
            $location.search('eventId', event.id).path('edit-event');
        }
        
        $scope.addEvent = function() {
            $location.path("edit-event");
        }

        init();

    }]);