select count(*) from books;

select count(*) from users;

select count(*) from book_borrow
where is_returned = 0;

select name from book_categories;

select name, isbn, year, author, description from books
where name = 'Agile Testing';




