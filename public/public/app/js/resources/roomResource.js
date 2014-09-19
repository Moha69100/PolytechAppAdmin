'use strict';

app.service('roomResource', ['$http', function($http){
        var root = this;
        
        this.rooms = [];
        
        this.findAll = function() {
            if (angular.isArray(root.rooms) && root.rooms.length !== 0) {
                return root.rooms;
            } else {
                root.rooms = [
                    {
                        'id': 1,
                        'libelle':'123',
                        'localisation':'premier etage',
                        'capacite':20
                    },
                    {
                        'id': 2,
                        'libelle':'123',
                        'localisation':'premier etage',
                        'capacite':18
                    }
                ] 
            }
            
            return root.rooms;
        }
}]);