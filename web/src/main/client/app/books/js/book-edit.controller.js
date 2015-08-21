angular.module('app.books').controller('BookEditController', function ($scope, bookService, $modalInstance) {
    'use strict';

    $scope.book = bookService.getBook();
    
    $scope.saveBook = function (book) {
        bookService.setBook(book);
        $modalInstance.close();
    };
    
    $scope.cancel = function () {
    	$modalInstance.dismiss('cancel');
    };
});
