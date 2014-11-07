app.controller("editRoomController", ['$scope', '$routeParams', 'roomResource', '$location',
    function($scope, $routeParams, roomResource, $location) {
        var init = function() {
            $scope.roomId = $routeParams.room;
            roomResource.getRoom({"id": $scope.roomId}, function(data) {
                $scope.room = data;
            });
        };

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.save = function() {
            
            roomResource.addRoom({}, $scope.room, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };
        
        $scope.update = function () {
            
            roomResource.updateRoom({}, $scope.room, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };

        $scope.removeRoom = function() {          
            roomResource.removeRoom({"id": $scope.roomId}, function () {
                $location.path('/admin-room');
            });            
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            delete ($scope.room);
            delete ($scope.roomId); 
            $location.path('/admin-room');
        };
        
        init();
    }]);


