package JobSchedular;

import Exception.RepeateIdException;
import CronJob.CronJob;
import Logger.LoggerInstance;

import java.util.HashSet;
import java.util.LinkedList;
import java.util.Timer;
import java.util.TimerTask;

public class CronJobScheduler extends Timer {

    private static CronJobScheduler myInstance = null;

    private LinkedList<TimerTask> jobsQueue;
    private HashSet<Long> jobIds;

    private CronJobScheduler(){
        jobsQueue = new LinkedList<TimerTask>();
        jobIds = new HashSet<>();
    }

    public static CronJobScheduler getInstance(){
        if(myInstance == null)
            myInstance = new CronJobScheduler();

            return myInstance;
    }

    public boolean enqueueAndScheduleJob(CronJob task, long delay, long period){
        if(myInstance == null)
            throw new RuntimeException("You need to create Job Scheduler first");
        //check if we have a previous job with same identifier
        if( idAlreadyExist(task.getId()) ){
            LoggerInstance.getTheInstance().logException("Repeated job Id",new RepeateIdException("Repeated job Id"));
            throw new RuntimeException("we have a job already with same Identifier ,Use another Identifier");
        }

        jobsQueue.addLast(task);
        jobIds.add(task.getId());
        myInstance.schedule(task,delay,period);
        return true;
    }

    public boolean idAlreadyExist(Long Id){
        return jobIds.contains(Id);
    }
}
