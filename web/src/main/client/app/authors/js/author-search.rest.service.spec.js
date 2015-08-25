describe('author rest service', function () {
	'use strict';
	
	beforeEach(function () {
		module('app.main');
		module('app.authors');
	});
	
	var scope, httpBackend, authorRestService;
	beforeEach(inject (function ($rootScope, $httpBackend, _authorRestService_) {
		scope = $rootScope.$new();
		httpBackend = $httpBackend;
		authorRestService = _authorRestService_;
	}));
	
	it('rest service should call GET', function () {
		// given
		// then
		httpBackend.expect('GET', '/context.html/rest/authors/all-authors').respond();
		// when
		authorRestService.search();
		httpBackend.flush();
	});
});