var helloAngularApp = angular.module('helloAngularApp', ['ui.router', 'ui.bootstrap', 'services', 'controllers']);

helloAngularApp.config(function ($stateProvider) {
    $stateProvider
        .state('login', {
            url: '/login.html'
        })
        .state('index', {
            url: '/'
        });
}).run(function ($state) {
    $state.go('index');
});