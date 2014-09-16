'use strict';

/**
 * Resource Requete
 */
app.factory('entrepriseResource', ['$resource', function ($resource) {

        var BASE_URL = '/entreprise/:id';
        var FIND_BY_ENTREPRISE_URL = BASE_URL + '/entreprise-id/:entrepriseId';

        var actions = {
            findByOffre:
                    {
                        method: 'GET',
                        isArray: true,
                        url: FIND_BY_ENTREPRISE_URL
                    },
            saveAllByOffre: {
                method: "POST",
                isArray: false,
                url: BASE_URL
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);
