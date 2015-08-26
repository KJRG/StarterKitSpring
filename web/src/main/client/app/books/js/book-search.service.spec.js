describe('book service should call book rest service', function () {
	'use strict';
	
	var bookService, bookRestServiceMock;
	beforeEach(function () {
		module('app.main');
		module('app.books', function ($provide) {
			bookRestServiceMock = jasmine.createSpyObj('bookRestService',
					['search', 'saveBook', 'editBookTitle', 'deleteBook']);
			$provide.value('bookRestService', bookRestServiceMock);
		});
		
		inject( function (_bookService_) {
			bookService = _bookService_;
		});
	});
	
	it('book service should call search', function () {
		// given
		var prefix = 'test';
		// when
		bookService.search(prefix);
		// then
		expect(bookRestServiceMock.search).toHaveBeenCalledWith(prefix);
	});
	
	it('book service should call saveBook', function () {
		// given
		var book = {
			id: 1,
			title: 'test'
		};
		// when
		bookService.saveBook(book);
		// then
		expect(bookRestServiceMock.saveBook).toHaveBeenCalledWith(book);
	});
	
	it('book service should call editBookTitle', function () {
		// given
		var book = {
			id: 1,
			title: 'test'
		};
		// when
		bookService.editBookTitle(book);
		// then
		expect(bookRestServiceMock.editBookTitle).toHaveBeenCalledWith(book);
	});
	
	it('book service should call deleteBook', function () {
		// given
		var bookId = 1;
		// when
		bookService.deleteBook(bookId);
		// then
		expect(bookRestServiceMock.deleteBook).toHaveBeenCalledWith(bookId);
	});
});