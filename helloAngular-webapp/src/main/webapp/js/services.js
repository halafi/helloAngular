var services = angular.module('services', ['ngResource']);

services.factory('UserService', ['$resource', function ($resource) {
    return $resource('rest/user/users:id', {id: '@id'}, {
        query: {
            method: 'GET', params: {}, isArray: true
        },
        post: {
            method: 'POST'
        }
        /*update: {},
        get: {},
        set: {},
        remove: {}*/
    });
}]);