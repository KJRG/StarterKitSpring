angular.module('app.books').factory('bookRestService', function ($http, currentContextPath) {
    'use strict';

    return {
        search: function (titlePrefix) {
            return $http.get(currentContextPath.get() + 'rest/books/books-by-title', {params: {titlePrefix: titlePrefix}});
        },
        saveBook: function (book) {
        	return $http.post(currentContextPath.get() + 'rest/books/book', book);
        },
        editBookTitle: function (book) {
        	return $http.post(currentContextPath.get() + 'rest/books/edit-book-title', book);
        },
        deleteBook: function (bookId) {
            return $http.delete(currentContextPath.get() + 'rest/books/book/' + bookId);
        }
    };
});
