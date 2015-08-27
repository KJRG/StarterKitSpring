describe('book controller', function () {
    'use strict';

    beforeEach(function () {
        module('app.main');
        module('flash');
        module('app.books');
    });

    var $scope;
    beforeEach(inject(function ($rootScope) {
        $scope = $rootScope.$new();
    }));

    it('search is defined', inject(function ($controller) {
        // when
        $controller('BookSearchController', {$scope: $scope});
        // then
        expect($scope.search).toBeDefined();
    }));

    it('search should call bookService.search', inject(function ($controller, $q, bookService) {
        //given
        $controller('BookSearchController', {$scope: $scope});
        var prefix = 'Jan';
        $scope.prefix = prefix;
        var result = {data: [{id: 5, title: 'Test 1', authors: [{id: 7, firstName: 'Jan', lastName: 'Kowalski'}]}] };
        $scope.books = [{id: 1, title: 'Book 1'}, {id: 2, title: 'Book 2'}];
        var searchDeferred = $q.defer();
        spyOn(bookService, 'search').and.returnValue(searchDeferred.promise);
        
        // when
        $scope.search();
        searchDeferred.resolve(result);
        $scope.$digest();
        
        // then
        expect(bookService.search).toHaveBeenCalledWith(prefix);
        expect($scope.books.length).toBe(1);
    }));

    it('delete book should call bookService.deleteBook', inject(function ($controller, $q, bookService, Flash) {
        // given
        $controller('BookSearchController', {$scope: $scope});

        var bookId = 1;
        $scope.books = [{id: bookId, title: 'test'}];
        var deleteDeferred = $q.defer();
        spyOn(bookService, 'deleteBook').and.returnValue(deleteDeferred.promise);
        spyOn(Flash, 'create');
        // when
        $scope.deleteBook(bookId);
        deleteDeferred.resolve();
        $scope.$digest();
        // then
        expect(bookService.deleteBook).toHaveBeenCalledWith(bookId);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została usunięta.', 'custom-class');
        expect($scope.books.length).toBe(0);
    }));
    
    it('addBook should open modal', inject(function ($controller, $modal, Flash) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.books = [{id: 1, title: 'test1', authors: []}];
    	var sampleBook = {
    		id: 2,
    		title: 'test2',
    		authors: []
    	};
    	var modalResult = {
    		then: function (callback) {
    			callback(sampleBook);
    		}
    	};
    	spyOn($modal, 'open').and.returnValue({result: modalResult});
    	spyOn(Flash, 'create');
    	
    	// when
    	$scope.addBook();
    	
    	// then
    	expect($modal.open).toHaveBeenCalledWith({
            templateUrl: 'books/html/add-book.html',
            controller: 'BookAddController',
            size: 'lg'
        });
        expect($scope.books.length).toBe(2);
        expect(Flash.create).toHaveBeenCalledWith('success', 'Książka została dodana.', 'custom-class');
    }));
    
    it('editBook should open modal', inject(function ($controller, $modal, bookService, Flash) {
    	// given
    	$controller('BookSearchController', {$scope: $scope});
    	$scope.books = [{id: 1, title: 'test', authors: []}];
    	var bookBeforeEditingTitle = {
    		id: 1,
    		title: 'test',
    		authors: []
    	};
    	var sampleBook = {
    		id: 1,
    		title: 'modified',
    		authors: []
    	};
    	var modalResult = {
    		then: function(callback) {
    			callback(sampleBook);
    		}
    	};
    	spyOn($scope, '_editResolve').and.callFake(function (item) {
    		return item;
    	});
    	spyOn($modal, 'open').and.returnValue({result: modalResult});
    	spyOn(bookService, 'editBookTitle');
    	spyOn(Flash, 'create');
    	
    	// when
    	$scope.editBook(bookBeforeEditingTitle);
    	
    	// then
    	expect($modal.open).toHaveBeenCalledWith({
            templateUrl: 'books/html/edit-book.html',
            controller: 'BookEditController',
            size: 'lg',
            resolve: {
            	book: bookBeforeEditingTitle
            }
        });
    	expect(bookService.editBookTitle).toHaveBeenCalledWith(sampleBook);
    	expect(Flash.create).toHaveBeenCalledWith('success', 'Tytuł książki został zmieniony.', 'custom-class');
    	expect($scope.books.length).toBe(1);
    	expect($scope.books[0].title).toEqual('modified');
    }));
});
