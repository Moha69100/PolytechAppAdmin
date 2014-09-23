app.controller("fileController", ['$scope', '$modal', "FileUploader",
    function ($scope, $modal, FileUploader) {
        $scope.uploader = new FileUploader();
        $scope.filePresent = [];
        $scope.fileType = [];
        $scope.linkToSave = [];
        $scope.autresFile = [];
        $scope.fileList = {};
        $scope.uploader.url = "/upload/add-file";

        var initFileList = function () {

            $scope.fileList = responseDemandeDoc;
            angular.forEach(responseDemandeDoc, function (file, key) {
                $scope.filePresent[file.type] = true;
            });
        };

        $scope.openDownloadPopup = function (fileTypeHtml) {
            $scope.fileType = fileTypeHtml;
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/test/upload-simple-modal.html',
                controller: 'uploadSimpleModalController',
                windowClass: "centered",
                scope: $scope,
                resolve: {
                    items: function () {
                        return {
                            uploader: $scope.uploader,
                            fileType: $scope.fileType
                        };
                    }
                }
            });
            modalInstance.result.then(function (result) {
                var FileItem = {
                    demandeId: $routeParams.id,
                    link: result.fileLink,
                    name: 'LINK',
                    type: result.fileType
                };
                $scope.filePresent[$scope.fileType] = true;
                $scope.linkToSave.push(FileItem);

            }, function (result) {
                if ($scope.filePresent[$scope.fileType]) {
                    $scope.filePresent[$scope.fileType] = false;
                    $scope.uploader.queue.pop();
                }
            });
        };
    }]);


