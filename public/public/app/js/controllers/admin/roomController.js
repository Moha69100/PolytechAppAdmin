'use strict';

app.controller('roomController', ['$scope', "$location", 'roomResource', '$sessionStorage', function($scope, $location, roomResource, storage){
    
    $scope.rooms = roomResource.findAll();
    $scope.currentRoom = storage.currentRoom || {};
    
    $scope.edit = function(data) {
        storage.currentRoom = data;
        $location.url('/admin-room-edit');
    }
    
        
}]);

