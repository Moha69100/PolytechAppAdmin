'use strict';

/**
 * Resource Requete
 */
app.factory('enterpriseResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8080';
        var FIND_ALL = BASE_URL + '/entreprises';
        var GET_BY_ID = BASE_URL + '/entreprise/:id';

        var actions = {
            listEnterprises:
                    {
                        method: 'GET',
                        isArray: true,
                        url: FIND_ALL
                    },
            getEnterprise: {
                method: 'GET',
                isArray: false,
                url: GET_BY_ID
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
