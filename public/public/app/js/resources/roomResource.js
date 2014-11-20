'use strict';

app.service('roomResource', ['$resource', function($resource) {

        var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090';
        var FIND_ALL = BASE_URL + '/salles';
        var GET_BY_ID = BASE_URL + '/salle/:id';
        var REMOVE_BY_ID = BASE_URL + '/salle/:id';
        var UPDATE = BASE_URL + '/salle';
        var CREATE = BASE_URL + '/salle/add';

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
            },
            addRoom: {
                method: 'PUT',
                url: CREATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);
    }]);