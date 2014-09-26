app.controller("fileController", ['$scope', '$modal', "FileUploader", "fileResource", "$http",
    function ($scope, $modal, FileUploader, fileResource, $http) {
        $scope.uploader = new FileUploader();
        $scope.filePresent = [];
        $scope.fileType = [];
        $scope.fileList = [];
        $scope.uploader.url = "http://localhost:8090/upload";

        var initFileList = function () {
            $http({
                method: "GET",
                url: "http://localhost:8090/files/student/1"}
            ).success(function (data) {
                angular.forEach(data, function (file, key) {
                    $scope.fileList.push({
                        name: file,
                        type: "CV",
                        fileType: "CV"
                    });
                    $scope.filePresent["CV"] = true;
                });
            });
        };
        initFileList();

        $scope.upload = function () {
            $scope.uploader.uploadAll();
        };

        $scope.uploader.onCompleteAll = function () {
            delete ($scope.filePresent);
            $scope.filePresent = [];
            $scope.uploader.clearQueue();
            $scope.uploader.url = "http://localhost:8090/upload";
        };

        $scope.openDownloadPopup = function (fileTypeHtml) {
            $scope.fileType = fileTypeHtml;
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/test/upload-simple-modal.html',
                controller: 'uploadSimpleModalController',
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


