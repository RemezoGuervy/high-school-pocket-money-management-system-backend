# high-school-pocket-money-management-system-backend

**Student Name:** Remezo Macari Guervy
**Student Id:** 27483
**Project Name:** High school pocket money management system

## Steps to run the API

1.install  Java 17 , Maven and PostgresSql.
2.Clone the repository.
3.In postgreSql, create a new database and call it 'pocket_money_db'.
4.Set database password : _____________________.
5. in the folder navigate to resource to application properties and then set the following : 
'spring.application.name=high-school-pocket-money-management-system

spring.datasource.url=jdbc:postgresql://localhost:5432/pocket_money_db
spring.datasource.username=your postgresSql username here
spring.datasource.password=your set password here

spring.jpa.hibernate.ddl-auto= update
spring.jpa.show-sql= true

server.port = your own port to be set here '.

6. Then after in the project folder open your terminal and then run : 'mvn spring-boot:run'.
7. The api will start at 'http://locahost: the port you set ', the Table are then created automatically 
