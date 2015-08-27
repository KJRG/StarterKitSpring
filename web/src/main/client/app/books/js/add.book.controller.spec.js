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
		$scope.book = sampleBook;
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
	
	it('addAuthor should open add author modal', inject(function ($modal) {
		// given
		var sampleBook = {
			id: 1,
			title: 'Test',
			authors: []
		};
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Author'
		};
		
		$scope.book = sampleBook;
		
		var modalResult = {
    		then: function (callback) {
    			callback(sampleAuthor);
    		}
    	};
    	spyOn($modal, 'open').and.returnValue({result: modalResult});
		
		// when
		$scope.addAuthor();
		
		// then
    	expect($modal.open).toHaveBeenCalledWith({
            templateUrl: 'books/html/add-author.html',
            controller: 'AuthorAddController',
            size: 'lg'
        });
		expect($scope.book.authors.length).toBe(1);
	}));
	
	it('addBook should call modalInstance.close', inject(function ($q, bookService) {
		// given
		var sampleBook = {
			id: 1,
			title: 'test',
			authors: []
		};
		
		var deferred = $q.defer();
		spyOn(bookService, 'saveBook').and.returnValue(deferred.promise);
		
		$scope.book = sampleBook;
		
		// when
		$scope.addBook();
		deferred.resolve({data: $scope.book});
		$scope.$digest();
		
		// then
		expect(bookService.saveBook).toHaveBeenCalledWith($scope.book);
		expect(modalInstance.close).toHaveBeenCalledWith($scope.book);
	}));
});