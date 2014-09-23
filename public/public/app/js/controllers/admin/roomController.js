'use strict';

app.controller('roomController', ['$scope', "$modal", 'roomResource', function($scope, $modal, roomResource) {

        $scope.rooms = {};

        $scope.editRoom = function(roomEdited) {
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/rooms/roomForm.html',
                controller: 'editRoomModalController',
                resolve: {
                    items: function() {
                        return {roomEdited: angular.copy(roomEdited), appel: "editRoom"};
                    }

                }
            });
            
            modalInstance.result.then(function(room) {
                ;
            }, function() {
                console.log("modal dismissed");
            });
        }

        var init = function() {
            roomResource.listRooms(function (data) {
                $scope.rooms = data;
            });
        }

        $scope.addRoom = function() {

        };

        init();

    }]);

