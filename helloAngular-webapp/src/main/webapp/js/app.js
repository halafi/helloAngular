var helloAngularApp = angular.module('helloAngularApp', []);

helloAngularApp.controller('UserController', function ($scope, $http) {
    $scope.users = {};
    $http.get('rest/user/users')
        .success(function (data, status, headers, config) {
            $scope.users = data;
        }).error(function (data, status, headers, config) {
            console.log("ajax error, getAllUsers");
        });
});