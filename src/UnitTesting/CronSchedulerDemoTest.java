package UnitTesting;

import CronJob.CronJob;
import JobSchedular.CronJobScheduler;
import MainClass.CronSchedulerDemo;
import Parser.Parser;
import Exception.RepeateIdException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.function.Executable;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CronSchedulerDemoTest {
    CronJobScheduler myTimer;
    Parser inputParser;
    Executable myFunc;

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        myTimer = CronJobScheduler.getInstance();
        inputParser = Parser.getInstance();

        myFunc = ()->{System.out.println("Hello World");};
    }

    @Test
    @DisplayName("Creating Two jobs with same ID gives Exception")
    public void doubleJobIds() throws RepeateIdException {
        //create the first Job
        String[] input1 = new String[4];
        input1[0] = "1";input1[1] = "2000";input1[2] = "3000";input1[3] = "3000";
        inputParser.validateJobId(input1[0]);
        CronJob tempJob = new CronJob.CronJobBuilder()
                .setIdentifier(Long.parseLong(input1[0]))
                .setSingleExpectedInterval(Long.parseLong(input1[1]))
                .setscheduleFrequency(Long.parseLong(input1[2]))
                .setmyfunction(myFunc)
                .build();
        myTimer.enqueueAndScheduleJob(tempJob,tempJob.getSingleExpectedInterval(),tempJob.getScheduleFrequency());

        //create second job with same id
        String[] input2 = new String[4];
        input2[0] = "1";input2[1] = "3000";input2[2] = "5000";input2[3] = "3000";
        //here we should get a RepeatIdException
        Assertions.assertThrows(Exception.class,()->{inputParser.validateJobId(input2[0]);} );

    }

    @DisplayName("Invalid Id Values")
    @ParameterizedTest
    @ValueSource(strings = {"khaled","_Zero","?"})
    public void InvalidId(String Id) throws RepeateIdException{
        //In this test I am using different invalid values for Id

        Assertions.assertThrows(NumberFormatException.class,()->{new CronJob.CronJobBuilder()
                .setIdentifier(Long.parseLong(Id))
                .setSingleExpectedInterval(Long.parseLong("2000"))
                .setscheduleFrequency(Long.parseLong("1000"))
                .setmyfunction(myFunc)
                .build();});
    }

    @DisplayName("Invalid Single Expected Interval Values")
    @ParameterizedTest
    @ValueSource(strings = {"khaled","_Zero","?"})
    public void InvalidSingleInterval(String singleInterval) {
        //In this test I am using different invalid values for Id
        Assertions.assertThrows(NumberFormatException.class,()->{new CronJob.CronJobBuilder()
                .setIdentifier(Long.parseLong("1"))
                .setSingleExpectedInterval(Long.parseLong(singleInterval))
                .setscheduleFrequency(Long.parseLong("1000"))
                .setmyfunction(myFunc)
                .build();});
    }

    @DisplayName("Invalid Frequency Interval Values")
    @ParameterizedTest
    @ValueSource(strings = {"khaled","_Zero","?"})
    public void InvalidFrequencyInterval(String scheduleFrequency){
        //In this test I am using different invalid values for Id
        Assertions.assertThrows(NumberFormatException.class,()->{new CronJob.CronJobBuilder()
                .setIdentifier(Long.parseLong("1"))
                .setSingleExpectedInterval(Long.parseLong("2000"))
                .setscheduleFrequency(Long.parseLong(scheduleFrequency))
                .setmyfunction(myFunc)
                .build();});
    }

    @DisplayName("Create multiple correct jobs and check if all is true")
    @Test
    public void createMultipleJobs(){
        //this test is used to create multiple correct jobs
        boolean allIsCreatedAndScheduled = false;
        for(int i = 10 ;  i <= 20 ;i++ ){
            CronJob tempJob = new CronJob.CronJobBuilder()
                    .setIdentifier(Long.parseLong(""+i))
                    .setSingleExpectedInterval(Long.parseLong(""+i*1000))
                    .setscheduleFrequency(Long.parseLong("" + i*1000))
                    .setmyfunction(myFunc)
                    .build();
            allIsCreatedAndScheduled = myTimer.enqueueAndScheduleJob(tempJob,tempJob.getSingleExpectedInterval(),tempJob.getScheduleFrequency());
        }
        Assertions.assertTrue(allIsCreatedAndScheduled);
    }
}