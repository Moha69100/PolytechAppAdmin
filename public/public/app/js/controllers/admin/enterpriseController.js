app.controller('enterpriseController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser',
    function($rootScope, $scope, $http, $timeout, $location, $window, browser) {



        var init = function() {
            $scope.enterprises = [{
                    id: 1,
                    raison: "entreprise 1",
                    naf: "00192U 04972304 5 5079",
                    siret: "5037FGFEIUB 9539057GF ",
                    effectif: "2498463",
                    organisme: "organisme 1",
                    adresse: "adresse 1",
                    adresse2: "adresse 2",
                    codePostal: "69003",
                    ville: "Lyon",
                    tel: "06 07 08 09 10",
                    anneeParticipForum: "2004",
                    nbApprentis: 4
                }];
        };

        $scope.addEnterprise = function() {
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/enterprises/enterpriseForm.html',
                controller: 'editEnterpriseModalController',
                resolve: {
                    items: function() {
                        return{};
                    }
                }
            });

            modalInstance.result.then(function(modifiedEnterprise) {
                $scope.enterprises[modifiedEnterprise.id] = modifiedEnterprise;
            }, function() {
                console.log("modal dismissed");
            });
        };

        $scope.editEnterprise = function(enterpriseEdited) {
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/enterprises/enterpriseForm.html',
                controller: 'editEnterpriseModalController',
                resolve: {
                    items: function() {
                        return {enterpriseEdited : angular.copy(enterpriseEdited), appel : "editEnterprise"};
                    }
                }
            });
            
            modalInstance.result.then(function(modifiedEnterprise) {
               $scope.enterprises[modifiedEnterprise.id] = modifiedEnterprise; 
            }, function() {
                console.log("modal dismissed");
            });
        };

        init();

    }]);