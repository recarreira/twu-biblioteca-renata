#Business Case
The Bangalore Public Library has approached us to design and implement a Library Management System for them. Based on their requirements, we have decided to develop a new system named __Biblioteca__. Since the library has a large list of requirements, we will be making multiple releases of Biblioteca, with each release incrementally adding more functionality. 

In order to easily add more functionality in the future as well as maintain a high level of quality, Biblioteca will be built using a __test driven__ approach.

The requirements for the first release of Biblioteca are given below.

##User Stories
- __Welcome Message__ - As a customer, I would like to see a welcome message when I start the application, so that I feel welcome and know that Biblioteca is available.
- __List Books__ - As a customer, after the welcome message appears I would like to see a list of all library books, so that I can browse for books I might want to check-out. Assume that there is a pre-existing list of books. You don't need to support adding or removing books from the library.
- __Book Details__ - As a customer, I'd like the list of all books to include each books Author and Year Published, so that I can be confident that I have found the book I am looking for. The book listing should have columns for this information.
- __Main Menu__  - As a customer, instead of automatically seeing the book list, I would like to see a list of options and be able to choose one. For now, the only option should be 'List Books'. All future options should be added to this menu also.
- __Invalid Menu__ Option - As a customer, I would like to be notified when I choose an invalid option with the message “Select a valid option!”, so that I can know when I need to re-enter my choice.
- __Quit__ - As a customer, I would like to continue choosing options until I choose to 'Quit'.
- __Checkout Book__ - As a librarian, I would like customers to be able to check-out a book. Checked out books should not appear in the list of all library books.
- __Successful__ Checkout - As a customer, I would like to know that a book has been checked out by seeing the message “Thank you! Enjoy the book”.
- __Unsuccessful__ Checkout - As a customer, I would like to be notified if the book I tried to check-out is not available by seeing the message, “That book is not available.”, so that I know that I need to select a different book or fix my spelling error.
- __Return Book__ - As a librarian, I would like customers to be able to return a book, so that other customers can check that book out. Returned books should appear in the list of library books.
- __Successful Return__ - As a customer, I would like to be notified if the book I am returning belongs to this library by seeing the message, “Thank you for returning the book.”, so that I know I returned the book to the right library.
- __Unsuccessful Return__ - As a customer, I would like to be notified if the book I am returning has not been added to this library by seeing the message, “That is not a valid book to return.”, so that I can return it to the correct library or fix my spelling error.

##Technical Requirements
- All the code __must__ be developed using TDD.
- Biblioteca needs to be a console application. Use your own imagination for any UI elements
- We do not need a database for this release.
