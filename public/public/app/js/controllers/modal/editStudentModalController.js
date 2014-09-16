app.controller("editStudentModalController", ['$scope', '$modalInstance', 'items', '$log', 'typeListeResource',
    function($scope, $modalInstance, items, $log, typeListeResource) {
        // 'feedback' serveur
        $scope.feedback = null;

        // objet éventuellement reçu en modification : binding avec page htlm
        if (typeof items.typeEdited == 'undefined') {
            $scope.typeListe = {};
        }
        else {
            $scope.typeListe = items.typeEdited;
        }
        //$log.info(' dans la modale : type donnee =', $scope.typeListe);// affiche objet avec son arborescence !

        // pour recuperer un parametre de la page appelante :
        //console.log("itemsappel=",items.appel);
        if (items.appel == 'student') {
            $scope.casEdit = true; // cas ajout
        }
        else {
            $scope.casEdit = false; // cas edition
        }

        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        };
        /**
         * sortie par saveType()
         */
        $scope.saveType = function() {
            //console.log("saveType typeListe=",$scope.typeListe);

            if ($scope.typeListe.$update) { // PUT : methode générée par la l'action update maison, absente de angular
                //console.log("cas update PUT !");

                $scope.typeListe.$update(function(response) {
                    $modalInstance.close($scope.typeListe.libListe);
                },
                        function(error) {
                            $scope.feedback = {error: error.data};
                            $log.error('fail update ', error);
                        });

            }
            else { // POST (entete req http, type interpreté par ZS comme create) : 
                // action générée par la fonction native angular .save , sur la ressource	
                typeListeResource.save($scope.typeListe, function(response) {
                    $modalInstance.close($scope.typeListe);
                },
                        function(error) {
                            $scope.feedback = {error: error.data};
                            $log.error('fail save ', error);
                        });
            }
        };
    }]);


