angular.module('app.books').controller('BookAddController', function ($scope, $modalInstance) {
	'use strict';
	
	$scope.book = {
		id : null,
		title : '',
		authors : []
	};
	
	$scope.author = {
        id : null,
        firstName : '',
        lastName : ''
    };
	
	$scope.addAuthor = function () {
		if($scope.author.firstName.length > 0 && $scope.author.lastName.length > 0) {
			$scope.book.authors.push(angular.copy($scope.author));
		}
	};
	
	$scope.addBook = function () {
		$modalInstance.close($scope.book);
	};
});