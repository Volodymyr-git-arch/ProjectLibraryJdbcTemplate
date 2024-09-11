# ProjectLibraryJdbcTemplate
## Project info

My project is a digital library,
a standard CRUD application in
which we
can access the Database through
a browser, and can ***create***, ***read***,
***update***, and ***delete*** 
objects in Database.

This application will
help to
store library information in
electronic form,
the librarian will
access the database and
receive all the necessary
information about books and
readers from the database,
as well as update the data.

Rows in the Database
correspond to objects
of our classes in the ***model***.



In this project we
use the DAO pattern
(Data Access Object),
this is a special classes
in which we write down
SQL queries to the Database,
that is, we take the logic of
work out of the ***model***
into special classes
PersonDAO and BookDAO.

In the ***model***  we have 2 classes:
***Person*** and ***Book***.

My application is
Spring Boot,
which contains a built-in web server.
Boot automatically configures
configuration and
dependency compatibility. To run
the application I only need to run
the main method in a special class.



To display the view, we use ***html***
forms with a ***thymeleaf***.
***Thymeleaf*** is a template
engine that
allows you to dynamically display
changes in forms.
## Testing logic
## 1.PostgreSQL database

We use a connection to the
PostgreSQL database.

In the database, there are 2 tables,
a ***person*** and a ***book***,
the relationship
between them is ***one to many***,
one person can have many books and 
one book can have only one owner. 

## 2.Models: Person and Book
In the model package we have 2 classes
***Person*** and ***Book***,
linked to tables in the database. The fields
in each class
are validated by annotations,
all errors when filling in
the fields are placed in the
***bindingResult*** object and Spring then
displays each error to the user. There are also
getters and setters.

## 3.PeopleController
### 1.List of all readers
http://localhost:8080/people -
By clicking on this ***url*** we
perform a GET request for the ***index*** method
PeopleController,and thus we get the entire
list of library readers.

In the library, you can get a list of all
readers on the page in the browser,
we can see the details of each person:
***fullName*** and ***yearOfBirth***.

There is also a link to create a new person,
after pressing the button the
following happens:
##### 1. GET request
GET request to method ***new person***
we get empty form of person.
##### 2. POST request
After filling it
click the button and
POST request
to method ***create***
creates a new person in
the database.
### 2.Individual person page
http://localhost:8080/people/id - GET request in
PeopleController, method ***show***.
For this ***url*** the user's
personal page is shown, you can also click
on a specific person in the general list.
On the person's page, there
are buttons:
##### 1. ***edit*** - editing person data
GET request on method ***edit*** in
PeopleController put an existing
person into a form,
after updating the form,
the data is sent by POST
request to the
***update*** method, updating data in the
database.

##### 2. ***delete*** - removal of a person
Deleting a person from the database
occurs
by a DELETE request to the controller method
***delete***.
##### 3. ***list of books***

The  ***list of books*** that stored
by this person is  displayed. 
If a person has not yet
taken any books, then 
the corresponding
inscription is displayed.

## 4.BooksController
### 1.General list of books
http://localhost:8080/books - we type in the
browser and get all books;


### 2. Individual book page
http://localhost:8080/books/id -  we type in the
browser and get a specific
book, or click
on a book in the general list.

Going to the book page, we have standard
links:
##### 1. Delete
##### 2. Edit
##### 3. Drop down list function

On the page of each specific book we have
a button with a drop-down list of all
readers, so if the book free,
we can assign this book to a
person, if the book is already occupied,
then we have a button to release the book
when the reader returns it back.

This is done by the controller methods
***release*** if the book is already occupied,
and ***assign*** if the book is free.

## 5.Information about some classes
### 1.PersonDAO аnd BookDAO.
In these classes we use
***JdbcTemplate***, which provides a higher
abstraction than the most
low-level
Jdbc API, in particular, 
with the help of
***BeanPropertyRowMapper***, we can map
rows from the Database to
objects of our model,
without manually writing
all setters. 

Also,
due to the fact that Jdbc Template
uses a PreparedStatement, the
threat of SQL injections is eliminated,
and the speed of
queries to the Database
also increases.

#### The following standart SCL requests are used in BookDAO:
1. SELECT * FROM Book - get
all books from Database
2. SELECT * FROM Book  WHERE id=? -
   get a specific book by ID.
3. INSERT INTO Book(title,author,year) 
VALUES( ?, ?, ?) - 
create a new book in the Database.
4. UPDATE Book SET title=?, 
author=?, year=? WHERE id=? - 
update the book with the
given ID in the Database.
5. DELETE FROM Book WHERE id=? -
   delete a book from the Database
by this ID.
 
#### Some special requests
 
6. SELECT Person.* FROM Book 
JOIN Person ON Book.person_id =
Person.id " +
   " WHERE Book.id=? - special  JOIN SQL 
request, connect 2 tables in
Database and get the owner of
the book by the ID
   of the book itself.
7. UPDATE Book SET person_id =
NULL WHERE id=? - assign the owner
   of the book to ***null***
   when a person
returns the book
into the library
(***release*** method).
8. UPDATE Book SET person_id=?
WHERE id = ? - assign a 
book to a person with a given ID
   ( ***assign*** method).

Similar SQL queries 
are used in PersonDAO.

## 2.PersonValidator
The ***PersonValidator*** class in the
***util*** package performs a more
complex Spring
validation than simple field validation.
It checks whether such a person
already exists in the database
by their ***fullName*** when a librarian
registers a person.
If it does, it returns
an error that such a person
already exists.


## Conclusion
Thus, this application allows you to carry
out the work of the library in a digital
format.


**Project author: Volodymyr
Krizhanivsky - Backend Engineer**




