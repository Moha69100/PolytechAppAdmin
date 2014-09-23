'use strict';

/**
 * Resource Requete
 */
app.factory('studentResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/etudiants';
        var GET_BY_ID = BASE_URL + '/etudiant/:id';

        var actions = {
            listStudents:
                    {
                        method: 'GET',
                        isArray: true,
                        url: FIND_ALL
                    },
            getStudent: {
                method: 'GET',
                isArray: false,
                url: GET_BY_ID
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);

