app.controller("editRoomModalController", ['$scope', '$modalInstance', 'items',
    function ($scope, $modalInstance, items) {
        // 'feedback' serveur
        $scope.feedback = null;

        $scope.room = items.roomEdited;

        $scope.save = function (room) {
            $scope.selected = {
                item: $scope.room
            };
            $modalInstance.close($scope.room);
        };
        
        $scope.removeRoom = function (room) {
            
            $modalInstance.dismiss();
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            $modalInstance.dismiss();
        };
    }]);


