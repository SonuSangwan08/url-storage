# url-storage-app
Application to manage URLs

Requirements:-
Java 11

Command to run application in local==>

./mvnw spring-boot:run

Application is configured to run at port 8085.

Application Url: http://localhost:8085/url-storage

Swagger Url : http://localhost:8085/url-storage/swagger-ui.html

Response type - JSON

Basic functionalities:-(Please go through the API documentation of swagger to check API details)

/url-info - POST - 201 status - returns Saved URL info

/url-info - GET - 'url' as parameter- 200 status - returns URL info

/url-info/count - GET - 'url' as parameter- 200 status - returns URL info

/url-info/list - GET - 'page' and 'size' as parameters- 200 status - returns List of URL info
