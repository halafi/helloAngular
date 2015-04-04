var controllers = angular.module('controllers', []);

controllers
    .controller('UserControllerList', ['$scope', 'UserService', function ($scope, User) {
        $scope.users = User.query();
    }])
    .controller('UserControllerCreate', ['$scope', 'UserService', function ($scope, User) {
        User.query(function(data){
            $scope.users = data;
        });
        $scope.createUser = function () {
            var response = User.save({}, $scope.userForm);
            console.log(response);

            //if (response.$resolved) {
                //$scope.users = User;
            //}
        };
    }]);