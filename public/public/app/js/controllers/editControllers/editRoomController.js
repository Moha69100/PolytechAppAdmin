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

        $scope.save = function(room) {
            var postData = {
                room: $scope.room
            };
            console.log("IN")
            roomResource.addRoom(postData, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };

        $scope.removeRoom = function(room) {          
            roomResource.removeRoom({"id": $scope.roomId}, function (data) {
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


