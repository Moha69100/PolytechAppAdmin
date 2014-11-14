'use strict';

angular.module('App').service('Authentication', ['$http', 'authService', 'AuthenticationData', function($http, authBuffer, authData){
    var root = this;
    var BASE_URL = 'http://localhost:8090';
    var apiKeyEndpoint = '/auth';
    
    this.login = function() {
        if (!authData.isAuthenticated()) {
            var promise = $http.get(BASE_URL + apiKeyEndpoint);
            promise.then(function(response) {
                authData.setApiKey(response.data.api_key);
            }).then(function() { 
                authBuffer.loginConfirmed()
            });
        }
    }

}]);