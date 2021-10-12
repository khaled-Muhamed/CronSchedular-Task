# CronSchedular-Challenge
## Project Title [CronJobSchedular-Task]

This is an in-process cron scheduler that accepts a job and executes it periodically.
Clients are able to specify:

* A unique job identifier.
* A single run expected interval
* Scheduling frequency
* The job implementation

## Getting Started
  * run the jar file and follow the instructions appear in console.
  * go and check the log file to see what happened.

## Prerequisites
java platform installed

## Important Files

 * src/CronJob/CronJob.java: This is a java class used for creating job instance and behaviour.
 * src/JobScheduler/CronJobScheduler: This is a java class to create the scheduler instance which is singleton and behaviour.
 * src/Logger/LoggerInstance: java class to log job actions which is singleton.
 * src/Parser/RunTimeValidator: java Interface to have the methods used to validate user input.
 * src/Parser/Parser: java class which is singleton to get user input parse and validate it.
 
## How to run
 * run the jar file
 * follow instruction in appear in console
    -->job creation is as follow:
      - jobId singleExpectedInterval SchedulingFrequency jobImplementation 

## References 
 *  https://www.geeksforgeeks.org/java-util-timertask-class-java/
 *  https://www.geeksforgeeks.org/java-util-timer-class-java/
 *  https://www.javatpoint.com/java-logger
 *  https://www.youtube.com/watch?v=W0_Man88Z3Q&t=542s&ab_channel=SimplyCoded
 *  https://www.codegrepper.com/code-examples/javascript/javascript+smooth+scroll+to+anchor+element
 * https://www.youtube.com/watch?v=flpmSXVTqBI&ab_channel=freeCodeCamp.org

## Authors
 Khaled Mohamed Abdelghany
