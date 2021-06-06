#Solution Insight
* This is a Spring Boot application with a very simple implementation.
* We have used the factory design pattern to facilitate smooth code 
  changes in case there is a new order processing which needs to be implemented.
* Extending functionality like unprocessed orders or saving the processed 
  ones can be added very easily.
* We have used a very simple approach of comparing the buy/sell price to match the order.
* Exception handling has been added for normal flow of the application.