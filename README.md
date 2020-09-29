REST Spring Boot Hibernate Demo Application

1. Storage
Mariadb is configured by default.
Create DB:
`CREATE DATABASE demo;`
Create User:
`CREATE USER 'user'@localhost IDENTIFIED BY 'demo';`
Grant priveleges:
`GRANT ALL PRIVILEGES ON demo.* TO 'user'@localhost;`

PostgreSQL dependency and configurations are present in configuration comments as well.
Alternatively one may use any RDBMS supported by Hibernate. Correct dependency and configuration (see application.properties) should be provided.
Hibernate is configured (auto-ddl = update) to provide schema if not present in a DB.

2. There is no security configured.

3. Cascade operations are not supported. To delete entity one must ensure there is no refference to it.

4. To run via maven (v.3.5):
`mvn spring-boot:run`

5. API usage examples (Swagger endpoint is not provided):
Create top category:
curl -X POST http://localhost:8080/api/v1/category/top
Create child category:
curl -X POST http://localhost:8080/api/v1/category/ch1/top
Create a product:
curl -X POST --data 'long string' -H "Content-Type:application/text" http://localhost:8080/api/v1/product/p7/29.12
Assign product to a category (use id and name from previous responses):
curl -X PUT http://localhost:8080/api/v1/product/17/ch1
Remove a category from product:
curl -X DELETE http://localhost:8080/api/v1/product/10/ch1
List products for category:
curl http://localhost:8080/api/v1/product/ch1
Get category children (use id from previous responses):
curl http://localhost:8080/api/v1/category/children/5
Delete category:
curl -X DELETE http://localhost:8080/api/v1/category/8
