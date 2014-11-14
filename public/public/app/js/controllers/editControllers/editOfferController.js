app.controller("editOfferController", ['$scope', 'offerResource', '$routeParams', '$location',
    function($scope, offerResource, $routeParams, $location) {

        var init = function() {
            $scope.offerId = $routeParams.offer;
            offerResource.getOffer({"id": $scope.offerId}, function(data) {
                $scope.offer = data;
            });
        };

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.save = function(offer) {
            var postData = $scope.offer;
            offerResource.updateOffer({}, postData);
        };

        $scope.removeOffer = function(offer) {
            offerResource.removeOffer({"id": $scope.offerId}, function(data) {
                $location.path('/admin-offer');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            delete ($scope.offer);
            delete ($scope.offerId);
            $location.url('/admin-offer');
        };
        init();
    }]);


