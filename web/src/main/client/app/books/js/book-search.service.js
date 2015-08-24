angular.module('app.books').factory('bookService', function (bookRestService) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return bookRestService.search(titlePrefix);
        },
        saveBook: function(book) {
        	return bookRestService.saveBook(book);
        },
        editBookTitle: function(book) {
        	return bookRestService.editBookTitle(book);
        },
        deleteBook: function (bookId) {
            return bookRestService.deleteBook(bookId);
        }
    };
});
