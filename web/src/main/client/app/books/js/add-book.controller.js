angular.module('app.books').controller('BookAddController', function ($scope, $modalInstance, $modal, bookService) {
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
		$modal.open({
            templateUrl: 'books/html/add-author.html',
            controller: 'AuthorAddController',
            size: 'lg'
        }).result.then(function (result) {
        	$scope.book.authors.push(result);
        });
	};
	
	$scope.addBook = function () {
		bookService.saveBook($scope.book).then(function (response) {
			$modalInstance.close(response.data);
		});
	};
});