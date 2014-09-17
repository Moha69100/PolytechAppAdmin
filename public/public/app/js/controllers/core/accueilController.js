'use strict';

app.controller('accueilController', ['$scope', function ($scope) {
        $scope.myInterval = 5000;
        var slides = $scope.slides = [];
        $scope.addSlide = function () {
            slides.push({
                image: 'img/quote.jpg',
            });
            slides.push({
                image: 'img/slide1.jpg',
            });
            slides.push({
                image: 'img/facts.jpg',
            });
        };
        var init = function () {
            // $scope.browser = (browser() == "firefox");
            //$scope.user = AuthService.user;
            $scope.addSlide();
        };
        /*
         * $scope.redirect = function(param) { if (param == "offres") {
         * $location.path("/offre-list"); } else if (param == "demandes") {
         * $location.path("/demande-list"); } else if (param == "saisons") {
         * $location.path("/roadmap"); } };
         */
        init();
    }]);