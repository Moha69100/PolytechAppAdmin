'use strict';

app.service('eventResource', ['$resource', '$q', '$timeout', function ($resource, $q, $timeout) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = '/evenements';
        var FIND_BY_ID = '/evenement/:id';
        var UPDATE = "/evenement";
        var PLANNING = "/planningGenerate/:id";

        var actions = {
            'listEvents': {
                'method': 'GET',
                'url': BASE_URL + FIND_ALL,
                'isArray': true
            },
            'getEvent': {
                'method': 'GET',
                'url': BASE_URL + FIND_BY_ID,
                'isArray': false
            },
            'updateEvent': {
                'method': 'POST',
                'url': BASE_URL + UPDATE,
                'isArray': false
            },
            'createEvent': {
                'method': 'PUT',
                'url': BASE_URL + UPDATE + "/add",
                'isArray': false
            },
            'generatePlanning': {
                'method': 'GET',
                'url': BASE_URL + PLANNING,
                'isArray': false
            },
            'deleteEvenement': {
                'method': 'DELETE',
                'url': BASE_URL + FIND_BY_ID,
                'isArray': false
            }
        };

        return $resource(BASE_URL, {'id': '@id'}, actions);
    }]);