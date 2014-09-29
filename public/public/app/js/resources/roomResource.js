'use strict';

app.service('roomResource', ['$resource', function($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/salles';
        var GET_BY_ID = BASE_URL + '/salle/:id';
        var REMOVE_BY_ID = BASE_URL + '/salle/:id';
        var UPDATE = BASE_URL + '/salle';

        var actions = {
            listRooms: {
                method: 'GET',
                isArray: true,
                url: FIND_ALL
            },
            getRoom: {
                method: 'GET',
                isArray: false,
                url: GET_BY_ID  
            }, 
            removeRoom: {
                method: 'DELETE',
                url: REMOVE_BY_ID
            },
            updateRoom: {
                method: 'POST',
                url: UPDATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);
    }]);