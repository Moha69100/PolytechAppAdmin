app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location", "$rootScope",
    function($scope, $routeParams, enterpriseResource, $location, $rootScope) {
        $scope.init = function() {
            
            if ($routeParams.enterprise) {

                $scope.enterpriseId = $routeParams.enterprise;
                return enterpriseResource.getEnterprise({"id": $scope.enterpriseId}, function(data) {
                    $scope.enterprise = data;
                }).$promise;
            } else {

            }
        };
        // 'feedback' serveur
        $scope.feedback = null;
        //     $scope.enterprise = items.enterpriseEdited;

        $scope.save = function() {
            var postData = $scope.enterprise;
            enterpriseResource.addEnterprise({}, postData, function() {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Entreprise ajoutée");
            }, function() {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de l'ajout");
            });
        };

        $scope.update = function(enterprise) {
            var postData = enterprise;
            enterpriseResource.updateEnterprise({}, postData, function() {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Entreprise enregistrée");
            }, function() {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la sauvergarde");
            });
        };


        $scope.removeEnterprise = function(enterprise) {
            enterpriseResource.removeEnterprise({"id": $scope.enterpriseId}, function(data) {
                $location.path('/admin-enterprise');
            }, function() {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la suppression");
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            delete ($scope.enterprise);
            delete ($scope.enterpriseId);
            $location.path('/admin-enterprise');
        };

        var promise = $scope.init();

        // ----------------- GESTION DES CONTACTS ------------------------------
        promise.then(function() {
            $scope.contactsToRemove = [];

            $scope.idActuelContact = 0;

            $scope.addContact = function() {
                $scope.enterprise.personnecontacts = $scope.enterprise.personnecontacts || []; 
                $scope.enterprise.personnecontacts.push({});
            }

            $scope.prepareRemoveContact = function(index) {
                if ($scope.contactsToRemove[index] == null) {
                    $scope.contactsToRemove[index] = $scope.enterprise.personnecontacts[index];
                } else {
                    $scope.contactsToRemove.splice(index, 1);
                }
                console.log($scope.contactsToRemove);
            }

            $scope.removeContact = function() {
                angular.forEach($scope.contactsToRemove, function(val1, key1) {
                    angular.forEach($scope.enterprise.personnecontacts, function(val2, key2) {
                        if (val1.id == val2.id) {
                            $scope.enterprise.personnecontacts.splice(key2, 1);
                        }
                    });
                });
                $scope.contactsToRemove = [];
            }
        })
        // ---------------------------------------------------------------------
    }]);

