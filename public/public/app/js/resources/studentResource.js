'use strict';

/**
 * Resource Requete
 */
app.factory('studentResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090/';
        var FIND_ALL = BASE_URL + '/etudiants';
        var GET_BY_ID = BASE_URL + '/etudiant/:id';
        var REMOVE_BY_ID = BASE_URL + '/etudiant/:id';
        var UPDATE = BASE_URL + '/etudiant';

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
            },
            removeStudent: {
                method: 'DELETE',
                url: REMOVE_BY_ID
            },
            updateStudent: {
                method: 'POST',
                url: UPDATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);

