# Crewmeister Test Assignment - Java Backend Developer


## The Challenge


**_Thi is the endpoint that I need to use to load the data -->_**
http://localhost:8080/api/loadData


- As a client, I want to get a list of all available currencies

**_Thi is the endpoint necessary to check this -->_**

http://localhost:8080/api/currencies


- As a client, I want to get all EUR-FX exchange rates at all available dates as a collection

**_Thi is the endpoint necessary to check this -->_**

http://localhost:8080/api/currencyexchanges


- As a client, I want to get the EUR-FX exchange rate at particular day

**_Thi is the endpoint necessary to check this -->_**

http://localhost:8080/api/exchangerates/1999-01-04


- As a client, I want to get a foreign exchange amount for a given currency converted to EUR on a particular day

**_Thi is the endpoint necessary to check this -->_**

http://localhost:8080/api/euroconverter/1999-01-04/



Swagger Endpoint :

	 http://localhost:8080/swagger-ui.html


## Setup
#### Requirements
- Java 11 (will run with OpenSDK 15 as well)
- Maven 3.x


````shell script
$ mvn spring-boot:run
````
