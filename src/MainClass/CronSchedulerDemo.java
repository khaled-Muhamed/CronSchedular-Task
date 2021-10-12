package MainClass;
import Exception.RepeateIdException;

import CronJob.CronJob;
import JobSchedular.CronJobScheduler;
import Parser.Parser;

import java.util.function.Function;

public class CronSchedulerDemo {

    public static void main(String[] args) throws RepeateIdException {
        Function<Long, String> myFunc = CronSchedulerDemo::myFunc;
        //1- get the instance of schedular
        CronJobScheduler myTimer = CronJobScheduler.getInstance();
        //1- get the instance of user input parse Validator
        Parser inputParser = Parser.getInstance();

        while(true){
            String[] input = inputParser.getUserInput();
            boolean isValidNumberOfParameters = inputParser.isValidParameters(input);
            boolean isValidId = inputParser.validateJobId(input[0]);
            boolean isValidSingleInterval = inputParser.validateSingleExpectedInterval(input[1]);
            boolean isValidScheduleFrequency = inputParser.validateSchedulingFrequency(input[02]);

            if(isValidNumberOfParameters && isValidId && isValidSingleInterval && isValidScheduleFrequency){
     //           System.out.println("All is good try Again");
                CronJob tempJob = new CronJob.CronJobBuilder()
                        .setIdentifier(Long.parseLong(input[0]))
                        .setSingleExpectedInterval(Long.parseLong(input[1]))
                        .setscheduleFrequency(Long.parseLong(input[2]))
                        .setmyfunction(myFunc)
                        .build();
                myTimer.enqueueAndScheduleJob(tempJob,tempJob.getSingleExpectedInterval(),tempJob.getScheduleFrequency());
            }

        }
    }
    public static String myFunc(Long x){
        return   "Executing function of job  "  + x ;
    }

}
