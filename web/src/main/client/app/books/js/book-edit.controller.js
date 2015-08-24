angular.module('app.books').controller('BookEditController', function ($scope, $modalInstance, book) {
    'use strict';

    $scope.book = book;
    
    $scope.saveBookTitle = function (book) {
        $modalInstance.close(book);
    };
});
