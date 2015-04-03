var controllers = angular.module('controllers', []);

controllers.controller('UserController', ['$scope', 'UserService', function ($scope, User) {
    $scope.users = User.query();
}]);