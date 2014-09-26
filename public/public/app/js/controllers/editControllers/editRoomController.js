app.controller("editRoomModalController", ['$scope', '$routeParams', 'roomResource',
    function($scope, $routeParams, roomResource) {
        $scope.init = function() {
            $scope.roomId = $routeParams.room;
            roomResource.getRoom({"id": $scope.roomId}, function(data) {
                console.log(data)
                $scope.room = data;
            });
        };

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.room = items.roomEdited;

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


