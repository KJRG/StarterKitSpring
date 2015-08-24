angular.module('app.authors').controller('AuthorSearchController', function ($scope, $window, $location, authorService) {
    'use strict';

    $scope.authors = [];
    $scope.gridOptions = { data: 'authors' };

	var init = function () {
		authorService.search().then(function (response) {
			angular.copy(response.data, $scope.authors);
		});
    };

	init();
});
