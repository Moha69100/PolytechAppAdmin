app.controller('enterpriseController', ['$scope', '$modal', function ($scope, $modal) {


        var init = function() {
            $scope.entreprises = [
             {"id": 0, "raison": "SASU Société par actions simplifiée à associé unique", "naf": "C'est quoi NAF ?",
             "siret": "48116365700010", "effectif": "60", "organisme": "CPAM", "adresse": "31 Rue Pauline Borghese",
             "adresse2": "", "cp": "92200", "ville": "NEUILLY SUR SEINE", "tel": "04 75 58 45 21",
             "anneeparticipforum": "2014", "nbrapprenti": 2, "entreprisepresences": [], "offrealternances": [], "entretiens": [], "personnecontacts": []
             },
             {"id": 1, "raison": "SASU Société par actions simplifiée à associé unique",
             "naf": "C'est quoi NAF ?", "siret": "48116365700010", "effectif": "60", "organisme": "CPAM",
             "adresse": "31 Rue Pauline Borghese", "adresse2": "", "cp": "92200", "ville": "NEUILLY SUR SEINE",
             "tel": "04 75 58 45 21", "anneeparticipforum": "2014", "nbrapprenti": 2, "entreprisepresences": [],
             "offrealternances": [], "entretiens": [], "personnecontacts": []
             },
             {"id": 2, "raison": "SASU Société par actions simplifiée à associé unique",
             "naf": "C'est quoi NAF ?", "siret": "48116365700010", "effectif": "60",
             "organisme": "CPAM", "adresse": "31 Rue Pauline Borghese", "adresse2": "",
             "cp": "92200", "ville": "NEUILLY SUR SEINE", "tel": "04 75 58 45 21",
             "anneeparticipforum": "2014", "nbrapprenti": 2, "entreprisepresences": [],
             "offrealternances": [], "entretiens": [], "personnecontacts": []},
             {"id": 3, "raison": "SASU Société par actions simplifiée à associé unique", "naf": "C'est quoi NAF ?", "siret": "48116365700010",
             "effectif": "60", "organisme": "CPAM", "adresse": "31 Rue Pauline Borghese", "adresse2": "", "cp": "92200",
             "ville": "NEUILLY SUR SEINE", "tel": "04 75 58 45 21", "anneeparticipforum": "2014",
             "nbrapprenti": 2, "entreprisepresences": [], "offrealternances": [], "entretiens": [], "personnecontacts": []
             },
             {"id": 4, "raison": "SASU Société par actions simplifiée à associé unique", "naf": "C'est quoi NAF ?", "siret": "48116365700010",
             "effectif": "60", "organisme": "CPAM", "adresse": "31 Rue Pauline Borghese", "adresse2": "", "cp": "92200",
             "ville": "NEUILLY SUR SEINE", "tel": "04 75 58 45 21", "anneeparticipforum": "2014", "nbrapprenti": 2,
             "entreprisepresences": [], "offrealternances": [], "entretiens": [], "personnecontacts": []
             }
             ];
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