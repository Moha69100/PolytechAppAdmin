app.controller('enterpriseController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function ($rootScope, $scope, $http, $timeout, $location, $window, browser) {

        

        var init = function () {
            $scope.enterprises = [{
                    id : 1,
                    raison : "entreprise 1", 
                    naf : "00192U 04972304 5 5079",
                    siret : "5037FGFEIUB 9539057GF ",
                    effectif : "2498463", 
                    organisme : "organisme 1", 
                    adresse : "adresse 1", 
                    adresse2 : "adresse 2", 
                    codePostal : "69003", 
                    ville : "Lyon", 
                    tel : "06 07 08 09 10", 
                    anneeParticipForum : "2004", 
                    nbApprentis : 4
                }];
        };

        init();

    }]);