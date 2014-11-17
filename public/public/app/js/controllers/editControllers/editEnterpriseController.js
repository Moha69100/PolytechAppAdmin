

app.controller("editEnterpriseController", ['$scope', '$routeParams', "enterpriseResource", "$location",
    function($scope, $routeParams, enterpriseResource, $location) {
        $scope.init = function() {
            if ($routeParams.enterprise) {

                $scope.enterpriseId = $routeParams.enterprise;
                enterpriseResource.getEnterprise({"id": $scope.enterpriseId}, function(data) {
                    $scope.enterprise = data;
                    $scope.enterprise.contacts = [];
                });
            } else {

            }
        };
        // 'feedback' serveur
        $scope.feedback = null;
        //     $scope.enterprise = items.enterpriseEdited;

        $scope.save = function() {
            var postData = $scope.enterprise;
            enterpriseResource.addEnterprise({}, postData);
        };

        $scope.update = function() {
            var postData = $scope.enterprise;
            enterpriseResource.updateEnterprise({}, postData);
        };


        $scope.removeEnterprise = function(enterprise) {
            enterpriseResource.removeEnterprise({"id": $scope.enterpriseId}, function(data) {
                $location.path('/admin-enterprise');
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

        $scope.init();

        // ----------------- GESTION DES CONTACTS ------------------------------
        $scope.contactsToRemove = [];
        $scope.mockcontacts = [{
                id: 1,
                nom: "TRICHARD",
                prenom: "Maxime",
                mail: "test@polytech.com"
            }, {
                id: 2,
                nom: "LEROUX",
                prenom: "Geoffrey",
                mail: "test2@polytech.com"
            }];

        $scope.addContact = function() {
            $scope.enterprise.contacts.push($scope.newContact);
            angular.forEach($scope.mockcontacts, function(val, key) {
                if (val.id == $scope.newContact.id) {
                    $scope.mockcontacts.splice(key, 1);
                }
            });
        }

        $scope.prepareRemoveContact = function(index) {
            if ($scope.contactsToRemove[index] == null) {
                $scope.contactsToRemove[index] = $scope.enterprise.contacts[index];
            } else {
                $scope.contactsToRemove.splice(index, 1);
            }
            console.log($scope.contactsToRemove);
        }

        $scope.removeContact = function() {
            angular.forEach($scope.contactsToRemove, function(val1, key1) {
                angular.forEach($scope.enterprise.contacts, function(val2, key2) {
                    if (val1.id == val2.id) {
                        $scope.enterprise.contacts.splice(key2, 1);
                        $scope.mockcontacts.push(val1);
                    }
                });
            });
            $scope.contactsToRemove = [];
        }



        // ---------------------------------------------------------------------
    }]);

