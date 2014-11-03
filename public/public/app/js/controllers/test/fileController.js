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
                url: "http://localhost:8090/upload/student/1"}
            ).success(function (data) {
                angular.forEach(data, function (file, key) {
                    $scope.fileList.push({
                        name: file.name,
                        type: "CV"
                    });
                    $scope.filePresent["CV"] = true;
                });
            });
            $scope.fileList.push({
                name: "CV",
                type: "CV"
            },{
                name: "lettreMotiv",
                type: "lettreMotiv"
            });
            $scope.filePresent["CV"] = true;
            $scope.filePresent["lettreMotiv"] = true;
        };

        $scope.removeFileFromQueue = function (item) {
            item.remove();
            $scope.filePresent[item.fileType] = false;
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

        $scope.openUploadPopup = function (fileTypeHtml) {
            $scope.fileType = fileTypeHtml;
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/test/upload-simple-modal.html',
                controller: 'uploadSimpleModalController',
                scope: $scope,
                resolve: {
                    items: function () {
                        return {
                            uploader: $scope.uploader,
                            fileType: fileTypeHtml
                        };
                    }
                }
            });

            modalInstance.result.then(function (result) {
                $scope.filePresent[$scope.fileType] = true;
                console.log($scope)
            }, function (result) {
                if ($scope.filePresent[$scope.fileType]) {
                    $scope.filePresent[$scope.fileType] = false;
                    $scope.uploader.queue.pop();
                }
            });
        };

    }]);


