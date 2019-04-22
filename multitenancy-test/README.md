# This is a project template for a Java EE web app with Jersey,Jackson and Hibernate, with a front end assembled by Webpack.

## Useage

Just clone this repo and get to work 

## Project Structure


```
|
+-pom.xml
+-src
   |    
   +-main
   |   |
   |   +java  <--- This is where our java classes go
   |   +-resources
   |   |     |
   |   |     +-META-INF
   |   |         |
   |   |         +-persistence.xml    
   |   +-webapp
   |        |
   |        +-package.json
   |        +-webpack.config.js
   |        +-src    <-- This is where we do our frontend work
   |           |
   |           +-app.js     <-- This is the entry point to our application
   |           |-swagger.html <-- set the openapi settings here
   |           +-styles
   |           |    |
   |           |    +-app.css
   |           |
   |           +-META-INF
   |           |    |
   |           |    +-context.xml
   |           |  
   |           +-WEB-INF
   |           |    |
   |           |    +-web-production.xml --> settings for production builds
   |           |    |
   |           |    +-web-development.xml --> settings for development builds
   |           |
   |           +-swagger-ui <-- swagger UI folder, do not touch
   +-test 
      |
      +java  <--java test classes go here

```
## Configuration

### pom.xml

In the file pom.xml, set your application name, and add any java dependencies you need.

### web-production.xml/web-development.xml

In the file src/main/webapp/src/WEB-INF/web.xml, set the base of your jersey API, 
your endpoints package, and a webcontext listener if you want to use one

### context.xml

In the file src/main/webapp/src/META-INF/context.xml , set the root of your application

### persistence.xml

In the file src/main/resource/META-INF/persistence.xml, set the name of your 
persistence unit, the JDBC connection string, and username and password for the
database.

### swagger.html

In the file src/main/webapp/src/swagger.html, enter the base of your jersey API
so you can access the swagger UI for testing.

## Building

development(non minified builds), including swagger-ui : 

```
mvn clean install -Dproduction=false

```

production(minified builds) : 

```
mvn clean install -Dproduction=true

```
## Using Webpack watching capabilities

Go to the src/main/webapp folder and run 

```
npm run watch
```

to use webpack file watching.
