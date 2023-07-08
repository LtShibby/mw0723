# mw0723
code assessment for interview

Created By: Matthew James Wozniak

LinkedIn:   https://www.linkedin.com/in/matthew-wozniak/


Tool Rental Application:
------------------------
This is a simple Java application that simulates a tool rental system for a hardware store.

Overview:
---------
Customers can rent tools for a specified number of days. At the time of checkout, a Rental Agreement is generated with details about the rental. The store charges a daily rental fee, with some tools free of charge on weekends or holidays.

Tools available for rental include a ladder, chainsaw, and two types of jackhammers.

Building the Application:
-------------------------
The application uses Maven for dependency management. To build the project, navigate to the project directory in a terminal and execute:
    mvn clean install

Running the Application:
------------------------
Since this is mainly a backend application without a user interface, the primary way to interact with it is through the included JUnit tests.

After building the application with Maven, the tests can be run with:
    mvn test

Running the Tests in VSCode:
----------------------------
If you are using Visual Studio Code with the Java Extension Pack, you can run the tests by opening the ToolRentalTests.java file and clicking on the "Run Test" link that appears above each test method.

Project Structure:
------------------
CardinalPOS/

|---src/

|---|--- main/java/com/cardinal/pos/ - Contains the main application classes

|---|----|--- models/                - Contains the Tool, RentalAgreement, and Holiday classes

|---|----|--- repositories/          - Contains the ToolRepository class, which serves as a simulated database

|---|----|--- services/              - Contains the RentalService class, which contains the business logic

|---|--- test/java/com/cardinal/pos/ - Contains the JUnit tests for the application
