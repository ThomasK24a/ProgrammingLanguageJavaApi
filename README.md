# ProgrammingLanguageJavaApi

API for the course Data Processing

Create by Thomas Koops 

##How to install

###Prerequisites
The project will require the following to run
* Maven
* A MySQL database
* Tomcat

XAMPP is recommended as it includes both phpMyAdmin and TomCat

###Configuration
Clone the project to the desired folder

Run the database.sql file in a MySQL database

Change the database config settings in Config.java to the correct username, password and database link

###Run the project
Use the Maven command "mvn clean install"

Export the generated war file inside /target/ to the webapps folder of TomCat

Head to http://localhost:8080/ProgrammingLanguageJavaApi_war/swagger-ui.html to open the swagger documentation

Alternatively, the following settings can be setup in IntelliJ 

![configuration settings](run configuration settings.PNG "configuration settings")

# Endpoints

The end points of the api are:
* http://localhost:8080/ProgrammingLanguageJavaApi_war/languages
* http://localhost:8080/ProgrammingLanguageJavaApi_war/issues
* http://localhost:8080/ProgrammingLanguageJavaApi_war/popularity

# Data
The data used is from the following data sets

* https://www.kaggle.com/ihelon/hello-world-in-programming-languages
* https://www.kaggle.com/jyotmakadiya/top-20-programming-languages-2021 
* https://www.kaggle.com/isaacwen/github-programming-languages-data