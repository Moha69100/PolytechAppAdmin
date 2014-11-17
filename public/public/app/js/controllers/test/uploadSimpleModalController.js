'use strict';

app.controller('uploadSimpleModalController', ['$scope', '$modalInstance', 'items', function ($scope, $modalInstance, items) {
        $scope.controlFilesValidation = function () {
            var i = 0;
            angular.forEach($scope.uploader.queue, function (value, key) {
                if (value.fileType == items.fileType) {
                    i++;
                    if (i > 1)
                        $scope.uploader.queue[key].remove();
                }
            });
        };

        $scope.valider = function (link, type) {
            //$scope.controlFilesValidation();
            $modalInstance.close({
                datas: $scope.uploader
            });
        };

        $scope.cancel = function () {
            $modalInstance.dismiss('cancel');
        };
        $modalInstance.opened.then(function () {

            if (items == null || items.uploader == null) {
                return;
            }
            $scope.uploader = items.uploader;

            if (items.fileType) {
                $scope.itemOptions = {
                    'fileType': items.fileType
                };
            }
            $scope.uploader.onAfterAddingFile = function (item) {
                $scope.selectedFile = item.file.name;
                $scope.filePresent[$scope.fileType] = true;
            };
        }, function () {
            $log.info('Modal dismissed at: ' + new Date());

        });

    }]);