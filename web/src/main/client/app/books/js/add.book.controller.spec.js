describe('book add controller', function () {
	'use strict';
	
	beforeEach( function () {
		module('app.main');
		module('app.books');
	});
	
	var $scope, ctrl, modalInstance, bookService;
	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		modalInstance = {
			close: jasmine.createSpy('modalInstance.close'),
			dismiss: jasmine.createSpy('modalInstance.dismiss'),
			result: {
				then: jasmine.createSpy('modalInstance.result.then')
			}
		};
		bookService = {
			saveBook: jasmine.createSpy('bookService.saveBook'),
			result: {
				then: jasmine.createSpy('bookService.result.then')
			}
		};
		var sampleBook = {
			id: 1,
			title: 'test',
			authors: []
		};
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Test'
		};
		$scope.book = sampleBook;
		$scope.author = sampleAuthor;
		ctrl = $controller('BookAddController', {
			$scope: $scope,
			$modalInstance: modalInstance,
			$bookService: bookService,
		});
	}));
	
	it('addAuthor is defined', inject(function () {
		// then
		expect($scope.addAuthor).toBeDefined();
	}));
	
	it('addBook is defined', inject(function () {
		// then
		expect($scope.addBook).toBeDefined();
	}));
	
	it('addAuthor should add author', inject(function () {
		// given
		var sampleBook = {
			id: 1,
			title: 'test',
			authors: []
		};
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Author'
		};
		$scope.book = sampleBook;
		$scope.author = sampleAuthor;
		
		// when
		$scope.addAuthor();
		
		// then
		expect($scope.book.authors.length).toBe(1);
	}));
	
	it('addBook should call modalInstance.close', inject(function ($q, bookService) {
		// given
		var sampleBook = {
			id: 1,
			title: 'test',
			authors: []
		};
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Author'
		};
		
		var deferred = $q.defer();
		spyOn(bookService, 'saveBook').and.returnValue(deferred.promise);
		
		$scope.book = sampleBook;
		$scope.author = sampleAuthor;
		$scope.addAuthor();
		
		// when
		$scope.addBook();
		deferred.resolve({data: $scope.book});
		$scope.$digest();
		
		// then
		expect(bookService.saveBook).toHaveBeenCalledWith($scope.book);
		expect(modalInstance.close).toHaveBeenCalledWith($scope.book);
	}));
});