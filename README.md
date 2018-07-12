# statistics
Real-time statistics for transactions from the last 60 seconds.

## Technologies used:
* Java 8
* SpringBoot 2.0.3
* Maven 3

## Endpoints:
1. POST /transactions
   Content-Type : application/json
   body : ```{"amount":100.0, "timestamp": 1531430212922}```
   response : Success = HttpStatus CREATED - 201
	      Failure = HttpStatus NO_CONTENT - 204

2. GET /statistics
   response :        ```json
                      {
			"sum": 0,
			"avg": 0,
    			"max": "-Infinity",
    			"min": "Infinity",
    			"count": 0
		      }
			 ``` 

## To Run
- Run StatisticsApplication.java
