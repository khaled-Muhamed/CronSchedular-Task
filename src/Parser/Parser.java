package Parser;

import JobSchedular.CronJobScheduler;
import Logger.LoggerInstance;
import Exception.RepeateIdException;
import Exception.InvalidSingleInterval;
import Exception.InvalidFrequency;
import java.util.Scanner;

public class Parser implements RuntTimeValidator{

    private static Parser parserInstance = null;
    private static CronJobScheduler scheuler;
    private static LoggerInstance logger;
    private Parser(){
        scheuler = CronJobScheduler.getInstance();
        logger = LoggerInstance.getTheInstance();
    }

    public static Parser getInstance(){
        if(parserInstance == null)
            parserInstance = new Parser();
        return parserInstance;
    }

    @Override
    public String[] getUserInput() {

        Scanner sc= new Scanner(System.in);
        System.out.print("Create a job separated by spaces as follow: \n jobId singleExpectedInterval SchedulingFrequency jobImplementation \n ");
        String input = sc.nextLine(); //reads string.

        return input.split(" ");
    }
    @Override
    public boolean isValidParameters(String[] input){
        if(input.length ==  4){
            return true;
        }
        System.out.println("Invalid number of parameters try again \n");
        return false;
    }
    @Override
    public boolean validateSingleExpectedInterval(String singleInterval) {
          try{
            Long value = Long.parseLong(singleInterval);
            return true;
          }catch (NumberFormatException e){
              System.out.println("Invalid Single Interval value try again \n");
              logger.logException("Repeated job Id",new InvalidSingleInterval("Invalid Single Interval Value"));
              return false;
          }
    }

    @Override
    public boolean validateSchedulingFrequency(String frequencyInterval) {
        try{
            Long value = Long.parseLong(frequencyInterval);
            return true;
        }catch (NumberFormatException e){
            System.out.println("Invalid Scheduling frequency value try again \n");
            logger.logException("Invalid Scheduling Frequency",new InvalidFrequency("Invalid Scheduling Frequency Value"));
            return false;
        }
    }

    @Override
    public boolean validateJobId(String jobId) throws RepeateIdException{
        try{
            Long value = Long.parseLong(jobId);
            if(scheuler.idAlreadyExist(value)){
                System.out.println("Already Existing Id, Id must be Unique try Again \n");
                logger.logException("Repeated job Id",new RepeateIdException("Repeated job Id"));
                throw new RepeateIdException("Repeated job Id");
            }
            return true;
        }catch (NumberFormatException e){
            System.out.println("Invalid Job Id make it of Long type and try again \n");
            return false;
        }
    }

    @Override
    public boolean validateJobFunction() {
        //to do after I know how to get a function as input
        return false;
    }


}
