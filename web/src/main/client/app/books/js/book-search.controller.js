angular.module('app.books').controller('BookSearchController', function ($scope, $window, $location, bookService, Flash, $modal) {
    'use strict';

    $scope.books = [];
    $scope.gridOptions = { data: 'books' };
    $scope.prefix = '';
    
    $scope._editResolve = function(item) {
    	return function() {
    		return angular.copy(item);
    	};
    };

	var editBookById = function (editedBook) {
		for (var i = 0; i < $scope.books.length; i = i + 1) {
			if ($scope.books[i].id === editedBook.id) {
				$scope.books[i].title = angular.copy(editedBook.title);
				break;
			}
		}
	};
	
    var removeBookById = function (bookId) {
        for (var i = 0; i < $scope.books.length; i = i + 1) {
            if ($scope.books[i].id === bookId) {
                $scope.books.splice(i, 1);
                break;
            }
        }
    };

    $scope.search = function () {
        bookService.search($scope.prefix).then(function (response) {
            angular.copy(response.data, $scope.books);
        }, function () {
            Flash.create('danger', 'Wyjątek', 'custom-class');
        });
    };

    $scope.deleteBook = function (bookId) {
        bookService.deleteBook(bookId).then(function () {
            removeBookById(bookId);
            Flash.create('success', 'Książka została usunięta.', 'custom-class');
        });
    };

    $scope.addBook = function () {
        $modal.open({
            templateUrl: 'books/html/add-book.html',
            controller: 'BookAddController',
            size: 'lg'
        }).result.then(function (result) {
        	$scope.books.push(result);
        	Flash.create('success', 'Książka została dodana.', 'custom-class');
        });
    };

    $scope.editBook = function (book) {
        $modal.open({
            templateUrl: 'books/html/edit-book.html',
            controller: 'BookEditController',
            size: 'lg',
            resolve: {
            	book: $scope._editResolve(book)
            }
        }).result.then(function (result) {
        	var book = result;
        	editBookById(book);
        	bookService.editBookTitle(book);
        	Flash.create('success', 'Tytuł książki został zmieniony.', 'custom-class');
        });
    };

});
