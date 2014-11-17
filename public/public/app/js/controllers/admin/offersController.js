app.controller('offersController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', 'browser', 'offerResource',
    function($rootScope, $scope, $http, $timeout, $location, $window, browser, offerResource) {



        var init = function() {
            offerResource.listOffers(function(data) {
                $scope.offers = data;
            });
        };
        
        $scope.addOffer = function() {
            console.log("test");
            $location.path('/edit-offer');
        };

        $scope.editOffer = function(offerEdited) {
            console.log("id : " + offerEdited.id)
            $location.search('offer', offerEdited.id).path('/edit-offer');
        };

        init();

    }]);