# CronSchedular-Challenge
## Project Title [CronJobSchedular-Task]

This is an in-process cron scheduler that accepts a job and executes it periodically and save job excution time in log file.

Clients are able to specify:
* A unique job identifier.
* A single run expected interval.
* Scheduling frequency.
* Job implementaion(function).

## Technical Decisions:
* I used Builder design pattern to build a job:
  - reason: each job has multiple attributes (e.g jobID, SingleExpectedInterval, scheduleFrequency and function) to make it's creation simple and readable.
  
*  I decided that schedular is a single object through the whole program so it is a singleton one in order not to have multiple schedulars and lose control
  
* I decided that logger to be single object:
  - reason: to allow only one job use the instance at a time (seems to be synchronous) to be thread safe.
  
* I decieded if a user entered multiple jobs with same Id execution stops throw an Exception(RepeatedIdException) and log Exception in log file
  - reason: each job has multiple attributes (e.g jobID, SingleExpectedInterval, scheduleFrequency and function)
  
* I created a parser class which is responsible to get user input and parse it to make sure that all is good and valid ones which is a singleton one also.
  
## Example
check Sample Runs/Example1 snippets and log File

## Possible future improvements.
* Try to take the function as input in console

## Prerequisites
java platform installed

## Important Files

 * src/CronJob/CronJob.java: This is a java class used for creating job instance and behaviour.
 * src/JobScheduler/CronJobScheduler: This is a java class to create the scheduler instance which is singleton and behaviour.
 * src/Logger/LoggerInstance: java class to log job actions which is singleton.
 * src/Parser/RunTimeValidator: java Interface to have the methods used to validate user input.
 * src/Parser/Parser: java class which is singleton to get user input parse and validate it.
 
## How to run
* case 1:
 - create your own jobs in main class
 - run the main method
 - check log file
 
* case 2:
  - run the jar file in path (out/artifacts/CronJobScheduler_Challenge_jar/CronJobScheduler-Challenge.jar) using command
      -- java -jar CronJobScheduler-Challenge.jar
  - follow instruction in appear in console
    -->job creation is as follow:
      -- jobId singleExpectedInterval SchedulingFrequency jobImplementation 
 - go check log file

## References 
 *  https://www.geeksforgeeks.org/java-util-timertask-class-java/
 *  https://www.geeksforgeeks.org/java-util-timer-class-java/
 *  https://www.javatpoint.com/java-logger
 *  https://www.youtube.com/watch?v=W0_Man88Z3Q&t=542s&ab_channel=SimplyCoded
 *  https://www.codegrepper.com/code-examples/javascript/javascript+smooth+scroll+to+anchor+element
 * https://www.youtube.com/watch?v=flpmSXVTqBI&ab_channel=freeCodeCamp.org
 * https://www.w3schools.com/java/java_lambda.asp

## Authors
 Khaled Mohamed Abdelghany
