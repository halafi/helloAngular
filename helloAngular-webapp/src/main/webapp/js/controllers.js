var controllers = angular.module('controllers', []);

controllers
    .controller('UserController', ['$scope', '$state', 'UserService', function ($scope, $state, User) {
        $scope.orderProp = "id";
        $scope.userForm = new User();
        $scope.userEditted = {};
        $scope.users = User.query();
        $scope.getTemplate = function (user) {
            if (user.id === $scope.userEditted.id) return 'edit';
            else return 'display';
        };
        $scope.deleteUser = function (id) {
            User.remove({userId: id}, function () {
                $scope.users = User.query();
            });
        };
        $scope.createUser = function () {
            $scope.userForm.$save(function () { /* alternative var response = User.save({}, $scope.userForm);*/
                //$state.go('index');
                $scope.users = User.query();
                $scope.userForm = new User();
            });
        };
        $scope.editUser = function (user) {
            $scope.userEditted = angular.copy(user);
        }
        $scope.updateUser = function (user) {
            User.update({userId: user.id}, user, function () {
                $scope.users = User.query();
                $scope.reset();
            })
        }
        $scope.reset = function () {
            $scope.userEditted = {};
        }
    }])
    .controller('LoginController', ['$scope', '$state', 'UserService', function ($scope, $state, User) {
        $scope.rememberMe = false;
        $scope.login = function() {
            console.log($scope.userName, $scope.password, $scope.rememberMe);
            $state.go("index");
            //UserService.authenticate($.param({username: $scope.username, password: $scope.password}), function(authenticationResult) {
            //    var authToken = authenticationResult.token;
            //    $rootScope.authToken = authToken;
            //    if ($scope.rememberMe) {
            //        $cookieStore.put('authToken', authToken);
            //    }
            //    UserService.get(function(user) {
            //        $rootScope.user = user;
            //        $location.path("/");
            //    });
            //});
        };
    }]);