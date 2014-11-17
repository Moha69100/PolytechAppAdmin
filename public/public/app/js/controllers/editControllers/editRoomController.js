app.controller("editRoomController", ['$scope', '$routeParams', 'roomResource', '$location', "$rootScope",
    function($scope, $routeParams, roomResource, $location, $rootScope) {
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
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Salle ajoutée");
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de l'ajout");
            });
        };
        
        $scope.update = function () {
            
            roomResource.updateRoom({}, $scope.room, function (data) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Salle enregistrée");
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la sauvegarde");
            });
        };

        $scope.removeRoom = function() {          
            roomResource.removeRoom({"id": $scope.roomId}, function () {
                $location.path('/admin-room');
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la suppression");
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


