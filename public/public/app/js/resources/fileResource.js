'use strict';

/**
 * Resource Requete
 */
app.factory('fileResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/files/student/:id';

        var actions = {
            getByStudents:
                    {
                        method: 'GET',
                        isArray: false,
                        url: FIND_ALL
                    }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
