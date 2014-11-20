'use strict';

angular.module('App').service('Authentication', ['$http', 'authService', 'AuthenticationData', function($http, authBuffer, authData){
    var root = this;
    var BASE_URL = 'http://projet-a5a.univ-lyon1.fr:8090/';
    var apiKeyEndpoint = '/auth';
    
    var isLogingIn = false;
    
    this.login = function() {
        if (!authData.isAuthenticated() && !isLogingIn) {
            isLogingIn = true;
            var promise = $http.get(BASE_URL + apiKeyEndpoint);
            promise.then(function(response) {
                authData.setApiKey(response.data.api_key);
            }).then(function() {
                isLogingIn = false;
                authBuffer.loginConfirmed()
            });
        }
    }

}]);