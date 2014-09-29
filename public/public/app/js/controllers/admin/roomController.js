app.controller('roomController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 'roomResource', "$modal",
    function($rootScope, $scope, $http, $timeout, $location, $window, browser, roomResource, $modal) {



        var init = function() {
            roomResource.listRooms(function (data) {
                $scope.rooms = data;
            });
        }

        $scope.addRoom = function() {
            $location.path('/edit-room');
        };
        
        $scope.editRoom = function(roomEdited) {
            $location.search('room', roomEdited.id).path('/edit-room');
        }

        init();

    }]);

