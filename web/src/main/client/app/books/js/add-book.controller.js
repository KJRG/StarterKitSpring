angular.module('app.books').controller('BookAddController', function ($scope, $modalInstance, bookService) {
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
		bookService.saveBook($scope.book).then(function (response) {
			$modalInstance.close(response.data);
		});
	};
});