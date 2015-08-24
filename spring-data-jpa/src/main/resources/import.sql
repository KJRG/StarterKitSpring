insert into book (id, title) values (1, 'Pierwsza książka');
insert into book (id, title) values (2, 'Druga książka');
insert into book (id, title) values (3, 'Trzecia książka');
insert into book (id, title) values (4, 'Czwarta książka');

insert into author (id, first_name, last_name) values (4, 'Jan', 'Kowalski');
insert into author (id, first_name, last_name) values (5, 'Zbigniew', 'Nowak');
insert into author (id, first_name, last_name) values (6, 'Janusz', 'Jankowski');

insert into book_author (book_id, author_id) values (1, 4);
insert into book_author (book_id, author_id) values (2, 5);
insert into book_author (book_id, author_id) values (3, 6);
insert into book_author (book_id, author_id) values (4, 4);
insert into book_author (book_id, author_id) values (4, 5);
insert into book_author (book_id, author_id) values (4, 6);