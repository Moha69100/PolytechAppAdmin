'use strict';

/**
 * Resource Requete
 */
app.factory('offerResource', ['$resource', function ($resource) {

        var BASE_URL = 'http://localhost:8090';
        var FIND_ALL = BASE_URL + '/offres';
        var GET_BY_ID = BASE_URL + '/offre/:id';
        var REMOVE_BY_ID = BASE_URL + '/offre/:id';
        var UPDATE = BASE_URL + '/offre';

        var actions = {
            listOffers:
                    {
                        method: 'GET',
                        isArray: true,
                        url: FIND_ALL
                    },
            getOffer: {
                method: 'GET',
                isArray: false,
                url: GET_BY_ID
            },
            removeOffer: {
                method: 'DELETE',
                url: REMOVE_BY_ID
            },
            updateOffer: {
                method: 'POST',
                url: UPDATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);

