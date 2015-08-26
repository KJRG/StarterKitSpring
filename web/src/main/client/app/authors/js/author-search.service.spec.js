describe('author service', function () {
	'use strict';
	
	var authorService, authorRestServiceMock;
	beforeEach(function () {
		module('app.main');
		module('app.authors', function ($provide) {
			authorRestServiceMock = jasmine.createSpyObj('authorRestService', ['search']);
			$provide.value('authorRestService', authorRestServiceMock);
		});
		
		inject(function (_authorService_) {
			authorService = _authorService_;
		});
	});
	
	it('authorService should call authorRestService', function () {
		// given when
		authorService.search();
		// then
		expect(authorRestServiceMock.search).toHaveBeenCalledWith();
	});
});