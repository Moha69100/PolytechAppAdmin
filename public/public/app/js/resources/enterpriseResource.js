'use strict';

/**
 * Resource Requete
 */
app.factory('enterpriseResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/entreprises';
        var GET_BY_ID = BASE_URL + '/entreprise/:id';
        var ADD_ENTERPRISE = BASE_URL + '/entreprise/add';
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
            addEnterprise: {
                method: 'PUT',
                url: ADD_ENTERPRISE
            }, 
            removeEnterprise: {
                method: 'DELETE',
                url: REMOVE_BY_ID
            },
            updateEnterprise: {
                method: 'POST',
                url: UPDATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
