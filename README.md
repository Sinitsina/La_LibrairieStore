# La Librairie Store

This is a java web application for online book shop. A user can register, pick up a book and, after authorization, make an order. It is also possible to add your favourite products to favourites, view the list of favourites and edit it.

## REST API that has the capabilities to:

User:
- register,
- authorize,
- change password,
- get list of users by id, email, login, phone number (for admin only).

Product:
- create a product,
- update a product,
- delete a product,
- add a product to favourities,
- delete a product from favourities,
- get list of (favourities) products.

Book: 
- create a book,
- update a book,
- delete a book,
- find a book by author, title, genre.

Order:
- make an order,
- cancel an order,
- complete an order,
- find an order.

Cart:
- add a product to cart,
- change quantity of products in cart,
- delete a product from cart,
- get all products from cart.


## Api endpoints documentation
![Swagger](https://github.com/Sinitsina/La_LibrairieStore/blob/main/src/main/resources/Swagger.png)

## Database schema
![DB](https://github.com/Sinitsina/La_LibrairieStore/blob/main/src/main/resources/DB.png)

## Technology
- Java 15
- Spring Boot
- Spring Data JPA
- Spring Security
- Hibernate
- PostgreSQL
- Swagger
- Gradle
- Flyway
- MapStruct
