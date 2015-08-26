describe('book rest service', function () {
	'use strict';
	
	beforeEach(function () {
		module('app.main');
		module('app.books');
	});
	
	var scope, httpBackend, bookRestService;
	beforeEach(inject(function ($rootScope, _bookRestService_, $httpBackend) {
		scope = $rootScope.$new();
		httpBackend = $httpBackend;
		bookRestService = _bookRestService_;
	}));
	
	it('rest service should call GET', function () {
		// given
		var prefix = 'test';
		var result = {data: [{id: 1, title: 'test1'}, {id: 2, title: 'test2'}, {id: 3, title: 'test3'}]};
		
		// then
		httpBackend.expect('GET', '/context.html/rest/books/books-by-title?titlePrefix=' + prefix).respond(result);

		// when
		bookRestService.search(prefix);
		httpBackend.flush();
	});

	it('rest service should call POST on adding book', function () {
		// given
		var book = {
			id: 1,
			title: 'test',
			authors: []
		};
		
		// then
		httpBackend.expect('POST', '/context.html/rest/books/book').respond(book);

		// when
		bookRestService.saveBook(book);
		httpBackend.flush();
	});

	it('rest service should call POST on editing book title', function () {
		// given
		var book = {
			id: 1,
			title: 'test',
			authors: []
		};
		
		// then
		httpBackend.expect('POST', '/context.html/rest/books/edit-book-title').respond(book);

		// when
		bookRestService.editBookTitle(book);
		httpBackend.flush();
	});

	it('rest service should call DELETE', function () {
		// given
		var bookId = 1;
		
		// then
		httpBackend.expect('DELETE', '/context.html/rest/books/book/' + bookId).respond();

		// when
		bookRestService.deleteBook(bookId);
		httpBackend.flush();
	});
});