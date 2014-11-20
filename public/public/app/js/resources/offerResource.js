'use strict';

/**
 * Resource Requete
 */
app.factory('offerResource', ['$resource', function($resource) {

        var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090/';
        var FIND_ALL = BASE_URL + '/offres';
        var GET_BY_ID = BASE_URL + '/offre/:id';
        var REMOVE_BY_ID = BASE_URL + '/offre/:id';
        var UPDATE = BASE_URL + '/offre';
        var CREATE = BASE_URL + '/offre/add';

        var actions = {
            listOffers: {
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
            },
            addOffer: {
                method: 'PUT',
                url: CREATE
            }
        };

        return $resource(BASE_URL, {id: '@id'}, actions);

    }]);

