package CronJob;

import Logger.LoggerInstance;
import org.junit.jupiter.api.function.Executable;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;
import java.util.function.Function;

public class CronJob extends TimerTask {


    private Long id;
    private Long SingleExpectedInterval;
    private Long scheduleFrequency;
    private Executable myFunction;
    private Long numberOfRuns;



    private CronJob(CronJobBuilder builder){
        this.id = builder.id;
        this.SingleExpectedInterval = builder.SingleExpectedInterval;
        this.scheduleFrequency = builder.scheduleFrequency;
        this.myFunction = builder.myfunction;
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
        try {
            DynamicFunction(temp,this.myFunction);
            log(null , temp.toString() + " Success" );
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            log(throwable, temp.toString());
        }

    }

    //this function will be dynamic as it needs the client to pass his own function
    public void DynamicFunction(StringBuilder temp,Executable function) throws Throwable {
        function.execute();
    }

    public static class CronJobBuilder{
        private Long id;
        private Long SingleExpectedInterval;
        private Long scheduleFrequency;
        private Executable myfunction;

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
        public  CronJobBuilder setmyfunction(Executable function){
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


    public void setMyfunction(Executable myfunction) {
        this.myFunction = myfunction;
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

    private void log(Throwable e,String message){
        LoggerInstance loggerInstance = LoggerInstance.getTheInstance();
        if(e == null){
            //we are logging success state
            loggerInstance.logMessage(message);
        }else{
            //we are logging an exception
            loggerInstance.logException(message,(Exception) e);
        }
    }
}
