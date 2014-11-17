'use strict';

angular.module('App').service('AuthenticationData', [function() {
    
    var apiKey = null;
    
    this.isAuthenticated = function() {
        //console.log(apiKey);
        return apiKey !== null;
    }
    
    this.getApiKey = function() {
        return apiKey;
    }
    
    this.setApiKey = function(key) {
        apiKey = key;
    }
}]);