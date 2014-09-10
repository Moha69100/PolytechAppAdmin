'use strict';

app.controller('confirmModalController', [ '$scope', '$modalInstance', 'items', function($scope, $modalInstance, items) {
	
	
	
	if (items != null) {
		$scope.messageConfirm = items;
	}
	else {
		$scope.messageConfirm = "";
	}
	
	
} ]);