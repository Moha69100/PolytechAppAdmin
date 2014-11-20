'use strict';

/**
 * Resource Requete
 */
app.factory('meetingResource', ['$resource',
    function($resource) {

        var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090';
        var FIND_ALL_BY_EVENT = BASE_URL + '/entretiensByEvent/:id_event';
        var FIND_ALL = BASE_URL + '/entretiens';
        var FIND_BY_ID = BASE_URL + '/entretien/:id';

        var actions = {
            listByEventId: {
                method: 'GET',
                isArray: true,
                url: FIND_ALL_BY_EVENT
            },
            getMeeting: {
                'method': 'GET',
                'url': BASE_URL + FIND_BY_ID,
                'isArray': false
            },
            listMeetings: {
                'method': 'GET', 
                'url': BASE_URL + FIND_ALL, 
                'isArray': true
            }
        };

        return $resource(BASE_URL, {
            id: '@id', 
            id_event: '@id_event'
        }, actions);

    }
]);