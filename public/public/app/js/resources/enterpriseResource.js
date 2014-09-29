'use strict';

/**
 * Resource Requete
 */
app.factory('enterpriseResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/entreprises';
        var GET_BY_ID = BASE_URL + '/entreprise/:id';
        var UPDATE = BASE_URL + '/entreprise/';
        var REMOVE_BY_ID = BASE_URL + '/entreprise/:id';

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
            }, 
            updateEnterprise: {
                method: 'POST',
                url: UPDATE
            }, 
            removeEnterprise: {
                method: 'DELETE',
                url: REMOVE_BY_ID
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
