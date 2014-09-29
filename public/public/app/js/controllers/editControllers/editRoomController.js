app.controller("editRoomController", ['$scope', '$routeParams', 'roomResource',
    function($scope, $routeParams, roomResource) {
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
            //$modalInstance.close($scope.room);
        };

        $scope.removeRoom = function(room) {

            //$modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            //$modalInstance.dismiss();
        };
        
        init();
    }]);


