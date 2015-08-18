insert into library (id, name) values (10, 'Biblioteka Miejska');
insert into library (id, name) values (11, 'Biblioteka Wrocławska');
insert into library (id, name) values (12, 'Biblioteka Państwowa');
insert into library (id, name) values (13, 'Biblioteka');

insert into book (id, title, library_id) values (1, 'Pierwsza książka', 10);
insert into book (id, title, library_id) values (2, 'Druga książka', 10);
insert into book (id, title, library_id) values (3, 'Trzecia książka', 10);
insert into book (id, title, library_id) values (4, 'Coś', 11);
insert into book (id, title, library_id) values (5, 'Lśnienie', 12);
insert into book (id, title, library_id) values (6, 'Hobbit, czyli tam i z powrotem', 10);
insert into book (id, title, library_id) values (7, 'Hobbit, czyli tam i z powrotem', 11);
insert into book (id, title, library_id) values (8, 'Hobbit, czyli tam i z powrotem', 12);
insert into book (id, title, library_id) values (9, 'Nowa książka', 13);

insert into author (id, first_name, last_name) values (7, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (8, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (9, 'Janusz', 'Jankowski');
insert into author (id, first_name, last_name) values (10, 'Stephen', 'King');
insert into author (id, first_name, last_name) values (11, 'John Ronald Reuel', 'Tolkien');

insert into book_author(book_id, author_id) values (1, 7);
insert into book_author(book_id, author_id) values (2, 8);
insert into book_author(book_id, author_id) values (3, 9);
insert into book_author(book_id, author_id) values (4, 10);
insert into book_author(book_id, author_id) values (5, 10);
insert into book_author(book_id, author_id) values (6, 11);
insert into book_author(book_id, author_id) values (7, 11);
insert into book_author(book_id, author_id) values (8, 11);
insert into book_author(book_id, author_id) values (9, 7);
