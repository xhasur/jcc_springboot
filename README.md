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

**_This is another endpoint to check this with pageable implementation -->_**

http://localhost:8080/api/currencyexchanges/pageable?page=1&size=20

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





<img width="1233" alt="Captura de Pantalla 2022-10-04 a la(s) 4 22 05 p m" src="https://user-images.githubusercontent.com/12173428/193932745-f766623f-3909-4c3d-b0f1-741f944767bc.png">
<img width="1373" alt="Captura de Pantalla 2022-10-04 a la(s) 4 22 56 p m" src="https://user-images.githubusercontent.com/12173428/193932750-880afb81-21b9-4be8-bdad-003ff7903516.png">
<img width="1344" alt="Captura de Pantalla 2022-10-04 a la(s) 4 23 40 p m" src="https://user-images.githubusercontent.com/12173428/193932758-e31d21af-94bd-42c9-968f-30070585e0db.png">
<img width="1297" alt="Captura de Pantalla 2022-10-04 a la(s) 4 23 53 p m" src="https://user-images.githubusercontent.com/12173428/193932766-8be5be01-4f0b-4e5b-9fd9-a883367a6ddb.png">
<img width="1367" alt="Captura de Pantalla 2022-10-04 a la(s) 4 24 05 p m" src="https://user-images.githubusercontent.com/12173428/193932768-39768655-040f-4e9f-9e2c-1b8dfcd9a342.png">

