'use strict';

angular.module('App').service('AuthenticationInterceptor', ['AuthenticationData', function(authData) {
    this.request = function(config) {
        if (authData.isAuthenticated()) {
            config.headers.api_key = authData.getApiKey();
        }
        
        return config;
    }
    
    
}]); 