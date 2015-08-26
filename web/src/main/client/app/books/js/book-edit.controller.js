angular.module('app.books').controller('BookEditController', function ($scope, $modalInstance, book) {
    'use strict';

    $scope.book = book;
    
    $scope.saveBookTitle = function () {
        $modalInstance.close($scope.book);
    };
});
