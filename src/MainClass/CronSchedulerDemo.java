package MainClass;
import Exception.RepeateIdException;

import CronJob.CronJob;
import JobSchedular.CronJobScheduler;
import Parser.Parser;

import java.util.function.Function;

public class CronSchedulerDemo {

    public static void main(String[] args) throws RepeateIdException {

        // --> commented code is for the parser input as cron Scheduler as linux
//        //1- get the instance of schedular
        CronJobScheduler myTimer = CronJobScheduler.getInstance();
//        //2- get the instance of user input parse Validator
//        Parser inputParser = Parser.getInstance();
//
//        while(true){
//            String[] input = inputParser.getUserInput();
//            boolean isValidNumberOfParameters = inputParser.isValidParameters(input);
//            boolean isValidId = inputParser.validateJobId(input[0]);
//            boolean isValidSingleInterval = inputParser.validateSingleExpectedInterval(input[1]);
//            boolean isValidScheduleFrequency = inputParser.validateSchedulingFrequency(input[02]);
//
//            if(isValidNumberOfParameters && isValidId && isValidSingleInterval && isValidScheduleFrequency){
//     //           System.out.println("All is good try Again");
//                CronJob tempJob = new CronJob.CronJobBuilder()
//                        .setIdentifier(Long.parseLong(input[0]))
//                        .setSingleExpectedInterval(Long.parseLong(input[1]))
//                        .setscheduleFrequency(Long.parseLong(input[2]))
//                        .setmyfunction(()->{System.out.println("Hello World");})
//                        .build();
//                myTimer.enqueueAndScheduleJob(tempJob,tempJob.getSingleExpectedInterval(),tempJob.getScheduleFrequency());
//            }
//
//        }

        //trying lambda function
        try {
            CronJob tempJob1 = new CronJob.CronJobBuilder()
                    .setIdentifier(Long.parseLong("1"))
                    .setSingleExpectedInterval(Long.parseLong("1000"))
                    .setscheduleFrequency(Long.parseLong("2000"))
                    .setmyfunction(() -> {
                        System.out.println("Hello World");
                    })
                    .build();
            myTimer.enqueueAndScheduleJob(tempJob1,tempJob1.getSingleExpectedInterval(),tempJob1.getScheduleFrequency());
        }catch(NumberFormatException e){
            throw new NumberFormatException();
        }

        try {
            CronJob tempJob2 = new CronJob.CronJobBuilder()
                    .setIdentifier(Long.parseLong("2"))
                    .setSingleExpectedInterval(Long.parseLong("1000"))
                    .setscheduleFrequency(Long.parseLong("2000"))
                    .setmyfunction(() -> {
                        System.out.println("Hello World2");
                    })
                    .build();
            myTimer.enqueueAndScheduleJob(tempJob2, tempJob2.getSingleExpectedInterval(), tempJob2.getScheduleFrequency());
        }catch(NumberFormatException e){
            throw new NumberFormatException();
        }

    }

}
