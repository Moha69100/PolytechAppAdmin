app.controller('planningController', ['$rootScope', '$scope', '$modalInstance', 
	function($rootScope, $scope, $modalInstance) {

        var init = function () {
            
        };

        init();
        
        // Code pour le planning à afficher dans un événement
        $scope.cancel = function() {
            $modalInstance.dismiss('cancel');
        }
}]);