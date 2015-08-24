angular.module('app.books').factory('bookService', function (bookRestService) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return bookRestService.search(titlePrefix);
        },
        saveBook: function(book) {
        	return bookRestService.saveBook(book);
        },
        deleteBook: function (bookId) {
            return bookRestService.deleteBook(bookId);
        }
    };
});
