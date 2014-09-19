'use strict';

/**
 * Resource Requete
 */
app.factory('enterpriseResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/entreprises';

        var actions = {
            listEnterprises:
                    {
                        method: 'GET',
                        isArray: true,
                        url: FIND_ALL
                    }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
