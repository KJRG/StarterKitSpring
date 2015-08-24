angular.module('app.books').controller('BookEditController', function ($scope, $modalInstance, book) {
    'use strict';

    $scope.book = book;
    
    $scope.saveBook = function (book) {
        $modalInstance.close(book);
    };
    
    $scope.cancel = function () {
    	$modalInstance.dismiss('cancel');
    };
});
