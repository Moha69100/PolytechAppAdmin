app.controller("editOfferController", ['$scope', 'offerResource', 'enterpriseResource', '$routeParams', '$location', '$rootScope',
    function ($scope, offerResource, enterpriseResource, $routeParams, $location, $rootScope) {

        $scope.offer = [];

        $scope.offer.entreprise;

        var init = function () {
            $scope.offerId = $routeParams.offer;
            offerResource.getOffer({"id": $scope.offerId}, function (data) {
                $scope.offer = data;
            });

            enterpriseResource.listEnterprises(function (data) {
                $scope.enterprises = data;
            });

        };


        // 'feedback' serveur
        $scope.feedback = null;

        $scope.saveOffer = function (offer) {
            offerResource.addOffer({}, $scope.offer, function (data) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Offre d'alternance ajoutée");
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de l'ajout");
            });
        };

        $scope.updateOffer = function () {

            offerResource.updateOffer({}, $scope.offer, function (data) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Offre d'alternance enregistrée");
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la sauvergarde");
            });
        };

        $scope.removeOffer = function (offer) {
            offerResource.removeOffer({"id": $scope.offerId}, function (data) {
                $location.path('/admin-offers');
            }, function (error) {
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Erreur lors de la suppression");
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            delete ($scope.offer);
            delete ($scope.offerId);
            $location.url($location.path());
            $location.url('/admin-offers');
        };

        init();
    }]);


