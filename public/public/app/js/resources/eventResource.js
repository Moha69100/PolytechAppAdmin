'use strict';

app.service('eventResource', ['$resource', '$q', '$timeout', function($resource, $q, $timeout) {
        
        var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090/';
        var FIND_ALL = '/evenements';
        var FIND_BY_ID = '/evenement/:id';
        var UPDATE = "/evenement";
        
        var actions = {
            'listEvents': {
                'method': 'GET',
                'url': BASE_URL + FIND_ALL,
                'isArray': true
            },
            'getEvent': {
                'method': 'GET',
                'url': BASE_URL + FIND_BY_ID,
                'isArray': false
            }, 
            'updateEvent': {
                'method': 'POST', 
                'url': BASE_URL + UPDATE,
                'isArray': false
            }
        };
        
        return $resource(BASE_URL, {'id':'@id'}, actions);
        // à retirer quand branché au backend
        
        var mock = function() {
            var root = this;
            this.data = [];
            
            var events = [
                {
                    'id': 1,
                    'typeevt': 'forum',
                    'dateevt': '13/04/2015',
                    'duree': '4',
                    'heuredebut': '14:00',
                    'heurefin':'18:00',
                    'description': 'Ceci est le premier évènement organisé à l\'aide de l\'outil polytech_admin'
                },
                {
                    'id': 2,
                    'typeevt': 'forum',
                    'dateevt': '13/04/2016',
                    'duree': '4',
                    'heuredebut': '14:00',
                    'heurefin':'18:00',
                    'description': 'Et cedi est le deuxième'
                }
            ]; 
                
           /* this.listEvents = function(callback) {
                return events;
            }*/

            this.getEvent = function(params, callback) {
                var deferred = $q.defer();
                var promise = deferred.promise;
                
                promise.then(callback);
                deferred.resolve(events[0]);
            }
        }
        
        return new mock();
                
                
        
        
}])