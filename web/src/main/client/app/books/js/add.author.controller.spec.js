describe('author add controller', function () {
	'use strict';
	
	beforeEach( function () {
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
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Test'
		};
		$scope.author = sampleAuthor;
		ctrl = $controller('AuthorAddController', {
			$scope: $scope,
			$modalInstance: modalInstance,
		});
	}));
	
	it('addAuthor is defined', inject(function () {
		// then
		expect($scope.addAuthor).toBeDefined();
	}));
	
	it('addAuthor should call modalInstance.close', inject(function () {
		// given
		var sampleAuthor = {
			id: 1,
			firstName: 'Test',
			lastName: 'Author'
		};
		$scope.author = sampleAuthor;
		
		// when
		$scope.addAuthor();
		
		// then
		expect(modalInstance.close).toHaveBeenCalledWith(sampleAuthor);
	}));
});