describe('author service', function () {
	'use strict';
	
	var $scope, authorService;
	beforeEach(function () {
		module('app.main');
		module('app.authors');
		
		inject( function (_authorService_) {
			authorService = _authorService_;
		});
	});
	
	beforeEach(inject (function ($rootScope) {
		$scope = $rootScope.$new();
	}));
	
	it('search should call authorRestService.search', inject(function ($q, authorRestService) {
		// given
		var result = {data: [{id: 1, firstName: 'Test', lastName: 'Author One'},
						{id: 2, firstName: 'Test', lastName: 'Author Two'},
						{id: 3, firstName: 'Test', lastName: 'Author Three'}] };
		var searchDeferred = $q.defer();
		spyOn(authorRestService, 'search').and.returnValue(searchDeferred.promise);
		// when
		authorService.search();
		searchDeferred.resolve(result);
		$scope.$digest();
		// then
		expect(authorRestService.search).toHaveBeenCalledWith();
	}));
});