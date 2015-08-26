describe('book service should call book rest service', function () {
	'use strict';
	
	var $scope, bookService;
	beforeEach(function () {
		module('app.main');
		module('app.books');
		
		inject( function (_bookService_) {
			bookService = _bookService_;
		});
	});
	
	beforeEach(inject (function ($rootScope) {
		$scope = $rootScope.$new();
	}));
	
	it('search should call bookRestService.search', inject(function ($q, bookRestService) {
		// given
		var prefix = 'test';
		var result = {data: [{id: 5, title: 'Test 1', authors: [{id: 7, firstName: 'Jan', lastName: 'Kowalski'}]}] };
		var searchDeferred = $q.defer();
		spyOn(bookRestService, 'search').and.returnValue(searchDeferred.promise);
		// when
		bookService.search(prefix);
        searchDeferred.resolve(result);
        $scope.$digest();
        // then
        expect(bookRestService.search).toHaveBeenCalledWith(prefix);
	}));
	
	it('saveBook should call bookRestService.searchBook', inject( function ($q, bookRestService) {
		// given
		var book = {
			id: 1,
			authors: [{id: 1, firstName: 'Jan', lastName: 'Kowalski'}]
		};
		var result = {data: [{id: 1, title: 'test', authors: [{id: 1, firstName: 'Jan', lastName: 'Kowalski'}]}] };
		var saveBookDeferred = $q.defer();
		spyOn(bookRestService, 'saveBook').and.returnValue(saveBookDeferred.promise);
		// when
		bookService.saveBook(book);
		saveBookDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(bookRestService.saveBook).toHaveBeenCalledWith(book);
	}));
	
	it('editBookTitle should call bookRestService.editBookTitle', inject( function ($q, bookRestService) {
		// given
		var book = {
			id: 1,
			title: 'test',
			authors: [{id: 1, firstName: 'Jan', lastName: 'Kowalski'}]
		};
		var result = {data: [{id: 1, title: 'test', authors: [{id: 1, firstName: 'Jan', lastName: 'Kowalski'}]}] };
		var editBookTitleDeferred = $q.defer();
		spyOn(bookRestService, 'editBookTitle').and.returnValue(editBookTitleDeferred.promise);
		// when
		bookService.editBookTitle(book);
		editBookTitleDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(bookRestService.editBookTitle).toHaveBeenCalledWith(book);
	}));
	
	it('deleteBook should call bookRestService.deleteBook', inject( function ($q, bookRestService) {
		// given
		var bookId = 1;
		spyOn(bookRestService, 'deleteBook');
		// when
		bookService.deleteBook(bookId);
		// then
		expect(bookRestService.deleteBook).toHaveBeenCalledWith(bookId);
	}));
});