# E-Commerce Backend

A Java/Spring Boot learning API with products, categories, users, carts, cart items, orders, and order items.

## Stack and structure

Java 17, Spring Boot 3.1.5, Spring Data JPA, MySQL, MapStruct, and Lombok.

Under `src/main/java/com/user/ecommerce_project/`:

- `controllers/`: REST endpoints.
- `business/`: services and request/response models.
- Other packages hold persistence and domain code; browse the source tree for implementation details.

## Local setup

Install JDK 17 and MySQL. Create a development database and configure `spring.datasource.url`, `spring.datasource.username`, and `spring.datasource.password` in `src/main/resources/application.properties`, or override them with Spring environment variables. Review the Hibernate schema setting before connecting to an existing database. Never commit real credentials.

```sh
git clone https://github.com/Murat-Selim/E-commerce_Project.git
cd E-commerce_Project
```

Windows:

```powershell
.\mvnw.cmd spring-boot:run
```

macOS/Linux:

```sh
sh mvnw spring-boot:run
```

Use the startup log for the server URL. Request routes are defined in `controllers/`; start with category/product data before exercising cart and order flows.

## Validation

Run `mvn test` using an installed Maven, or the platform-specific wrapper above. Tests loading the Spring context may need the database. The documentation does not imply that payment processing or a production storefront is implemented.

## License

No repository-wide license file is currently declared.
