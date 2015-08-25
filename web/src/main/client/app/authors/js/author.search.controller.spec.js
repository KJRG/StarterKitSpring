describe('author controller', function () {
	'use strict';
	
	beforeEach(function () {
		module('app.main');
		module('app.authors');
	});
	
	var $scope;
	beforeEach(inject(function ($rootScope) {
		$scope = $rootScope.$new();
	}));
	
	it('init is defined', inject(function ($controller) {
		// when
		$controller('AuthorSearchController', {$scope: $scope});
		// then
		expect($scope.init).toBeDefined();
	}));
	
	it('init shoud call AuthorService.search', inject(function ($controller, $q, authorService) {
		// given
		var searchDeferred = $q.defer();
		spyOn(authorService, 'search').and.returnValue(searchDeferred.promise);
		$controller('AuthorSearchController', {$scope: $scope});
		expect(authorService.search).toHaveBeenCalledWith();
		$scope.authors = [{id: 1, firstName: 'Test', lastName: 'Author'}];
		var result = {data: [{id: 2, firstName: 'Test', lastName: 'Author'}]};
		// when
		$scope.init();
		searchDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(authorService.search).toHaveBeenCalledWith();
		expect($scope.authors.length).toBe(1);
	}));
});