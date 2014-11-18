app.controller("editStudentController", ['$scope', 'studentResource', '$routeParams', '$location', "FileUploader", "$http", "$modal", "AuthenticationData",
    function ($scope, studentResource, $routeParams, $location, FileUploader, $http, $modal, AuthenticationData) {
        $scope.uploader = new FileUploader();
        $scope.filePresent = [];
        $scope.fileType = [];
        $scope.fileList = [];
        $scope.uploader.url = "http://localhost:8090/upload/student/" + $routeParams.student;
        $scope.uploader.withCredentials = true;
        if (AuthenticationData.isAuthenticated()) {
            $scope.uploader.headers.api_key = AuthenticationData.getApiKey();
        }

        var init = function () {
            $scope.studentId = $routeParams.student;
            studentResource.getStudent({"id": $scope.studentId}, function (data) {
                $scope.student = data;
                initFileList();
            });
        };
        init();
        // 'feedback' serveur
        $scope.feedback = null;
        $scope.bacList = [
            {"name": "S"},
            {"name": "L"},
            {"name": "ES"}
        ];
        $scope.statCandidature = [
            {"name": "Acceptée"},
            {"name": "En cours"},
            {"name": "Rejetée"}
        ];
        $scope.save = function (student) {
            var postData = $scope.student;
            studentResource.updateStudent({}, postData, function () {

                $scope.uploader.uploadAll();
            });
        };
        $scope.uploader.onBeforeUploadItem = function (item) {
            item.url = "http://localhost:8090/upload/student/" + $routeParams.student + "/type/" + item.fileType;
        };


        $scope.removeStudent = function (student) {
            studentResource.removeStudent({"id": $scope.studentId}, function (data) {
                $location.path('/admin-student');
            });
        };
        /**
         * sortie par cancel()
         */
        $scope.cancel = function () {
            delete ($scope.student);
            delete ($scope.studentId);
            $location.url('/admin-student');
        };
        var initFileList = function () {
            $http({
                method: "GET",
                url: "http://localhost:8090/upload/student/files/" + $routeParams.student}
            ).success(function (data) {
                angular.forEach(data, function (file, key) {
                    $scope.fileList.push({
                        name: file[0],
                        type: file[1]
                    });
                    $scope.filePresent[file[1]] = true;
                });
            });
        };
        $scope.removeFileFromQueue = function (item) {
            item.remove();
            $scope.filePresent[item.fileType] = false;
        };
        $scope.upload = function () {
            $scope.uploader.uploadAll();
        };
        $scope.uploader.onCompleteAll = function () {
            delete ($scope.filePresent);
            $scope.filePresent = [];
            $scope.uploader.clearQueue();
            $scope.uploader.url = "http://localhost:8090/upload/student/" + $routeParams.student;
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
                console.log($scope.uploader)
            }, function (result) {
                if ($scope.filePresent[$scope.fileType]) {
                    $scope.filePresent[$scope.fileType] = false;
                    $scope.uploader.queue.pop();
                }
            });
        };
    }]);


