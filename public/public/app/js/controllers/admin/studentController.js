app.controller('studentController', ['$rootScope', '$scope', '$http', '$timeout', '$location', '$window', "studentResource", "$modal", "FileUploader", "AuthenticationData",
    function ($rootScope, $scope, $http, $timeout, $location, $window, studentResource, $modal, FileUploader, AuthenticationData) {
        $scope.uploader = new FileUploader();

        if (AuthenticationData.isAuthenticated()) {
            $scope.uploader.headers.api_key = AuthenticationData.getApiKey();
        }

        $scope.uploader.withCredentials = true;
        $scope.filePresent = [];
        $scope.fileType = [];
        $scope.fileList = [];

        var init = function () {
            studentResource.listStudents(function (data) {
                $scope.students = data;
            });
        };

        $scope.editStudent = function (studentEdited) {
            $location.search('student', studentEdited.id).path('/edit-student');
        };
        $scope.addStudent = function () {

            $scope.uploader.url = "http://localhost:8090/etudiants/upload";
            $scope.upload = function () {
                $scope.uploader.uploadAll();
            };
            $scope.uploader.onCompleteAll = function () {
                $scope.uploader.clearQueue();
                init();
                $rootScope.$broadcast(Events.Modale.OPEN_DIALOG_CONFIRM, "Fichier importé avec succès");
            };
            var modalInstance = $modal.open({
                templateUrl: 'app/partials/test/upload-simple-modal.html',
                controller: 'uploadSimpleModalController',
                scope: $scope,
                resolve: {
                    items: function () {
                        return {
                            uploader: $scope.uploader
                        };
                    }
                }
            });
            modalInstance.result.then(function (result) {
                $scope.uploader.uploadAll();
            });
        };
        init();
    }]);