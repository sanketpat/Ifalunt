'use strict';
var app = angular.module('iflaunt', [ 'ngRoute', 'ngMaterial', 'ngMessages',
		'ngTouch' ]);
app.config([ '$routeProvider', function($routeProvider) {

	$routeProvider.when('/login', {
		templateUrl : '/index.html',
		controller : 'LoginController'
	});
	$routeProvider.when('/register', {
		templateUrl : '/index.html',
		controller : 'RegisterController'
	});
	$routeProvider.when('/upload', {
		templateUrl : './profile-update.html',
		controller : 'fupController'
	});

} ]);

;
