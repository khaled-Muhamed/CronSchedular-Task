package CronJob;

import Logger.LoggerInstance;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.function.Function;

public class CronJob extends TimerTask {


    private Long id;
    private Long SingleExpectedInterval;
    private Long scheduleFrequency;
    private Function<Long , String> myfunction;
    private Long numberOfRuns;



    private CronJob(CronJobBuilder builder){
        this.id = builder.id;
        this.SingleExpectedInterval = builder.SingleExpectedInterval;
        this.scheduleFrequency = builder.scheduleFrequency;
        this.myfunction = builder.myfunction;
        this.numberOfRuns = Long.valueOf(0);
    }

    @Override
    public void run() {
        StringBuilder temp = new StringBuilder();
        temp.append("Job " + this.id+"  ");
        if(numberOfRuns == Long.valueOf(0)){
            temp.append("First run ");
            numberOfRuns++;
        }
        //get the time of execution
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss:ms");
        Date date = new Date();

        temp.append("Executing at  " +formatter.format(date)+"  ");
//        System.out.println(temp.toString());
        //going to log
 //      log(temp.toString());
        DynamicFunction(temp,this.myfunction);
    }

    //this function will be dynamic as it needs the client to pass his own function
    //input must be integer and output will be String
    public void DynamicFunction(StringBuilder temp,Function<Long , String> function) {
        String result = function.apply(this.id);
//        log(result);
        temp.append(result);
        log(temp.toString());
//        System.out.println(result);
    }

    public static class CronJobBuilder{
        private Long id;
        private Long SingleExpectedInterval;
        private Long scheduleFrequency;
        private Function<Long , String> myfunction;

        public  CronJobBuilder setIdentifier(long identifier){
            this.id = identifier;
            return this;
        }
        public  CronJobBuilder setSingleExpectedInterval(long SingleExpectedInterval){
            this.SingleExpectedInterval = SingleExpectedInterval;
            return this;
        }
        public  CronJobBuilder setscheduleFrequency(long scheduleFrequency){
            this.scheduleFrequency = scheduleFrequency;
            return this;
        }
        public  CronJobBuilder setmyfunction(Function<Long , String> function){
            this.myfunction = function;
            return this;
        }
        public CronJob build(){
            return new CronJob(this);
        }
    }

    public void setSingleExpectedInterval(Long singleExpectedInterval) {
        SingleExpectedInterval = singleExpectedInterval;
    }

    public void setScheduleFrequency(Long scheduleFrequency) {
        this.scheduleFrequency = scheduleFrequency;
    }

    public void setMyfunction(Function<Long, String> myfunction) {
        this.myfunction = myfunction;
    }

    public Long getId() {
        return id;
    }

    public Long getSingleExpectedInterval() {
        return SingleExpectedInterval;
    }

    public Long getScheduleFrequency() {
        return scheduleFrequency;
    }

    private void log(String message){
        LoggerInstance loggerInstance = LoggerInstance.getTheInstance();
        loggerInstance.logMessage(message);

    }
}
