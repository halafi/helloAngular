var controllers = angular.module('controllers', []);

controllers
    .controller('UserController', ['$scope', '$state', 'UserService', function ($scope, $state, User) {
        $scope.orderProp = "id";
        $scope.userForm = new User();
        $scope.model = {selected: {}};
        $scope.users = User.query();
        $scope.getTemplate = function (user) {
            if (user.id === $scope.model.selected.id) return 'edit';
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
            $scope.model.selected = angular.copy(user);
        }
        $scope.updateUser = function (user) {
            User.update({userId: user.id}, user, function () {
                console.log(user);
                $scope.users = User.query();
                $scope.model.selected = {};
                //$scope.userForm = new User();
            })
        }
    }])