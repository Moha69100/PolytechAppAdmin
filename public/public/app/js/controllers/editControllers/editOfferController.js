app.controller("editOfferController", ['$scope', 'offerResource', 'enterpriseResource', '$routeParams', '$location',
    function($scope, offerResource, enterpriseResource, $routeParams, $location) {

        $scope.offer = [];
        
        $scope.offer.attendingEnterprises = [];

        var init = function() {
            $scope.offerId = $routeParams.offer;
            offerResource.getOffer({"id": $scope.offerId}, function(data) {
                $scope.offer = data;
            });

            enterpriseResource.listEnterprises(function(data) {
                $scope.enterprises = data;
            });

            $scope.offer.attendingEnterprises.push($scope.attendingEnterprise);
        };
        
        $scope.attendingEnterprise = null;

        // 'feedback' serveur
        $scope.feedback = null;

        $scope.saveOffer = function(offer) {
            offerResource.addOffer({}, $scope.offer, function (data) {
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };
        
        $scope.updateOffer = function () {
            
            offerResource.updateOffer({}, $scope.offer, function (data) {
                console.log($scope.offer);
                console.log(data + "success");
            }, function (error) {
                console.log(error + " error ");
            });
        };

        $scope.removeOffer = function(offer) {
            offerResource.removeOffer({"id": $scope.offerId}, function(data) {
                $location.path('/admin-offers');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function() {
            delete ($scope.offer);
            delete ($scope.offerId);
            $location.url('/admin-offers');
        };
        
        init();
    }]);


