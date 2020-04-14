-- select all book
select name,author,genre,publisher,pages,price,ISBN from book;
--select all user
select name,password from user;
-- select all users name
select name from user;
-- when user use search with author
select name,author,genre,publisher,pages,price,ISBN from book where author=?;
-- when user use search with bookname
select name,author,genre,publisher,pages,price,ISBN from book where bookname=?;
-- when user use search with ISBN
select name,author,genre,publisher,pages,price,ISBN from book where ISBN=?;

-- user register
insert into user(name,password) values (?,?);
-- insert book
insert into book(name, author, genre, publisher, pages, price,ISBN) values(?,?,?,?,?,?,?);

--delete book
delete from book where name=?;