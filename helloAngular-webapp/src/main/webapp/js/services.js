var services = angular.module('services', ['ngResource']);

services.factory('UserService', ['$resource', function ($resource) {
    return $resource('http://localhost:8080/helloAngular/rest/user/:userId', {userId: '@id'}, {
        query: {
            method: 'GET', isArray: true, interceptor: {responseError: handleResponseError}
        },
        save: {
            method: 'POST', interceptor: {responseError: handleResponseError}
        },
        update: {
            method: 'PUT', interceptor: {responseError: handleResponseError}
        },
        get: {
            method: 'GET', interceptor: {responseError: handleResponseError}
        },
        remove: {
            method: 'DELETE', interceptor: {responseError: handleResponseError}
        }
    });
}]);

function handleResponseError() {
    alert("Internal server error.");
}
