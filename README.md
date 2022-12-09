# Football manager ⚽️ [REST API]

Application to operate with CRUD operations of players and teams.

### Required to install

- Java 17
- PostgreSQL

### How to run

Before we start, you need to open PSQL Terminal and create a DB,
You can achieve it with this command

```
CREATE DATABASE manager;
```

The server runs on 8080 port (By default), but if you have any conflicts, you can change it here too.
Username and password for the DB you can configure while creating database.

```
#application.properties
server.port = 8080

jdbc.driverClassName=org.postgresql.Driver
jdbc.url=jdbc:postgresql://localhost:5432/manager
jdbc.username=postgres
jdbc.password=421970
hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect
hibernate.show_sql=false
hibernate.format_sql=true
hibernate.hbm2ddl.auto=create
```

### Database

The project is already include all pre-data you need to test the application,
so you could avoid creating players and teams to work with. 

`import.sql` includes the data you need to test it