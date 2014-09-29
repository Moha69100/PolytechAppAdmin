app.controller("editRoomController", ['$scope', '$routeParams', 'roomResource', '$location',
    function($scope, $routeParams, roomResource, $location) {
        var init = function() {
            $scope.roomId = $routeParams.room;
            roomResource.getRoom({"id": $scope.roomId}, function(data) {
                console.log(data)
                $scope.room = data;
            });
        };

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.save = function(room) {
            roomResource.updateRoom({"id": $scope.roomId}, function (data) {
                console.log(data);
                $location.path('/edit-room');
            });
        };

        $scope.removeRoom = function(room) {          
            roomResource.removeRoom({"id": $scope.roomId}, function (data) {
                console.log(data);
                $location.path('/admin-room');
            });            
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            $location.path('/admin-room');
        };
        
        init();
    }]);


