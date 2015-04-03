var services = angular.module('services', ['ngResource']);

services.factory('UserService', ['$resource', function ($resource) {
    return $resource('rest/user/users', {}, {
        query: {
            method: 'GET', params: {}, isArray: true
        }/*,
        update: {},
        get: {},
        set: {},
        remove: {}*/
    });
}]);