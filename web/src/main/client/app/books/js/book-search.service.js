angular.module('app.books').factory('bookService', function (bookRestService) {
    'use strict';

	var book = '';
	
	var setBook = function (newBook) {
		var bookToSet = angular.copy(newBook);
		book = bookToSet;
	}

	var getBook = function () {
		return book;
	}

    return {
        search: function (titlePrefix) {
            return bookRestService.search(titlePrefix);
        },
        saveBook: function(book) {
        	return bookRestService.saveBook(book);
        },
        deleteBook: function (bookId) {
            return bookRestService.deleteBook(bookId);
        },
        setBook : setBook,
        getBook : getBook
    };
});
