angular.module('app.books').controller('AuthorAddController', function ($scope, $modalInstance) {
	'use strict';
	
	$scope.author = {
        id : null,
        firstName : '',
        lastName : ''
    };
	
	$scope.addAuthor = function () {
		$modalInstance.close($scope.author);
	};
});