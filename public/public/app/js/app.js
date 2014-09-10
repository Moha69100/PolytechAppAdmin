var app = angular.module('App', ['ui.bootstrap', 'ngRoute']);

app.config(['$routeProvider', function ($routeProvider) {

        $routeProvider.when('/admin-enterprise', {
            controller: 'enterpriseController',
            templateUrl: 'app/partials/admin/enterprises.html'
        }).when('/admin-user', {
            controller: 'userController',
            templateUrl: 'app/partials/admin/users.html'
        }).otherwise({
            controller: 'accueilController',
            redirectTo: '',
            templateUrl: 'app/partials/core/accueil.html'
        });

    }]);
/** sur démarrage de l'application */
app.run(['$rootScope', function ($rootScope) {
        /*
         $rootScope.$on(Events.Modale.OPEN_DIALOG_CONFIRM, function (event, data) {
         
         var modalInstance = $modal.open({
         windowClass: "confirmation",
         templateUrl: 'app/partials/core/confirmation.html',
         controller: 'confirmModalController',
         backdrop: 'static',
         resolve: {
         items: function () {
         return data;
         }
         
         }
         });
         
         modalInstance.opened.then(function () {
         setTimeout(function () {
         modalInstance.close();
         }, 1000);
         });
         });*/
        /*
         $rootScope.openConfirmModal = function (data) {
         $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, data);
         };
         
         $rootScope.$on('$routeChangeSuccess', function (event, next, current) {
         // console.info('>>> $routeChangeSuccess : $location path=',
         // $location.path());
         // console.info('>>> $routeChangeSuccess : current=', current);
         // console.info('>>> $routeChangeSuccess : next=', next);
         
         if (current && current.menu && current.menu.id) {
         // console.info('>>> $routeChangeSuccess : current.menu.id=',
         // current.menu.id);
         $('#' + current.menu.id).removeClass('active');
         var dropdown = $('#' + current.menu.id).parents('div.dropdown');
         var dropdownToggle = $(".dropdown-toggle", dropdown);
         dropdownToggle.removeClass('active');
         }
         
         if (next && next.menu && next.menu.id) {
         // console.info('>>> $routeChangeSuccess : next.menu.id=',
         // next.menu.id);
         $('#' + next.menu.id).addClass('active');
         var dropdown = $('#' + next.menu.id).parents('div.dropdown');
         var dropdownToggle = $(".dropdown-toggle", dropdown);
         dropdownToggle.addClass('active');
         }
         
         });
         
         }]);
         
         /**
         * Http Provider : handler default success / error
         */

    }]);
app.config(['$httpProvider', function ($httpProvider) {
        /*
         $httpProvider.responseInterceptors.push(['$q', '$rootScope', function ($q, $rootScope) {
         return function (promise) {
         return promise.then(function (response) {
         // do something on success
         // console.info('>> myHttpInterceptor - success', response);
         return response || $q.when(response);
         
         }, function (rejection) {
         // console.info('>> myHttpInterceptor - error', rejection);
         
         // do something on error
         if (rejection.status == 400) { // <=>
         // console.log("httpProvider --> 400 -
         // FunctionalException");
         // FunctionalException
         return $q.reject(rejection);
         
         } else if (rejection.status == 401) {
         // console.log("httpProvider -> 401 - vous n etes pas
         // autorisé");
         // unauthorized
         $rootScope.$broadcast(Events.Modale.OPEN_DIALOG, rejection);
         return $q.reject(rejection);
         // return;
         
         } else {
         $rootScope.$broadcast(Events.Modale.OPEN_DIALOG, rejection);
         return $q.reject(rejection);
         // return;
         }
         });
         };
         }]);*/
    }]);

app.config(['$httpProvider', function ($httpProvider) {

        // Ajax indicator
        //   $httpProvider.interceptors.push('ajaxIndicatorHttpInterceptor');
    }]);

// register the interceptor as a service, intercepts ALL angular ajax http calls
app.factory('ajaxIndicatorHttpInterceptor', ['$q', '$rootScope', function ($q, $rootScope) {
        /*    return {
         // optional method
         'request': function (config) {
         // do something on success
         // console.log("ajaxIndicatorHttpInterceptor >>> request");
         $rootScope.isLoading = true;
         return config || $q.when(config);
         },
         // optional method
         'requestError': function (rejection) {
         // do something on error
         // console.log("ajaxIndicatorHttpInterceptor >>> requestError");
         return $q.reject(rejection);
         },
         // optional method
         'response': function (response) {
         // do something on success
         // console.log("ajaxIndicatorHttpInterceptor >>> response");
         $rootScope.isLoading = false;
         return response || $q.when(response);
         },
         // optional method
         'responseError': function (rejection) {
         // do something on error
         // console.log("ajaxIndicatorHttpInterceptor >>> responseError");
         $rootScope.isLoading = false;
         return $q.reject(rejection);
         }
         
         };****/

    }]);


