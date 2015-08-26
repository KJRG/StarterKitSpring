describe('book edit controller', function () {
	'use strict';
	
	beforeEach( function() {
		module('app.main');
		module('app.books');
	});
	
	var $scope, ctrl, modalInstance;
	beforeEach(inject(function($rootScope, $controller) {
		$scope = $rootScope.$new();
		modalInstance = {
			close: jasmine.createSpy('modalInstance.close'),
			dismiss: jasmine.createSpy('modalInstance.dismiss'),
			result: {
				then: jasmine.createSpy('modalInstance.result.then')
			}
		};
		var b = {
			id: 1,
			title: 'test',
			authors: []
		};
		ctrl = $controller('BookEditController', {
			$scope: $scope,
			$modalInstance: modalInstance,
			book: b
		});
	}));
	
	it('saveBookTitle is defined', inject( function () {
		// then
		expect($scope.saveBookTitle).toBeDefined();
	}));
	
	it('saveBookTitle should call modalInstance.close', inject( function () {
		// given
		var book = {
			id: 1,
			title: 'test',
			authors: []
		};
		$scope.book = book;
		// when
		$scope.saveBookTitle();
		// then
		expect(modalInstance.close).toHaveBeenCalledWith(book);
	}));
});