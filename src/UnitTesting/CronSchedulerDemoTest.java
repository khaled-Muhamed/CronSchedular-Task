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
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.function.Function;

import static org.junit.jupiter.api.Assertions.*;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CronSchedulerDemoTest {
    CronJobScheduler myTimer;
    Parser inputParser;
    Function<Long, String> myFunc;

    @org.junit.jupiter.api.BeforeAll
    void setUp() {
        myTimer = CronJobScheduler.getInstance();
        inputParser = Parser.getInstance();

        myFunc = CronSchedulerDemo::myFunc;
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
        Assertions.assertFalse(inputParser.validateJobId(Id));
    }

    @DisplayName("Invalid Single Expected Interval Values")
    @ParameterizedTest
    @ValueSource(strings = {"khaled","_Zero","?"})
    public void InvalidSingleInterval(String singleInterval) {
        //In this test I am using different invalid values for Id
        Assertions.assertFalse(inputParser.validateSingleExpectedInterval(singleInterval));
    }

    @DisplayName("Invalid Frequency Interval Values")
    @ParameterizedTest
    @ValueSource(strings = {"khaled","_Zero","?"})
    public void InvalidFrequencyInterval(String scheduleFrequency){
        //In this test I am using different invalid values for Id
        Assertions.assertFalse(inputParser.validateSchedulingFrequency(scheduleFrequency));
    }

    @DisplayName("Create multiple correct jobs and check if all is true")
    @Test
    public void createMultipleJobs(){
        //this test is used to create multiple correct jobs
        boolean allIsCreatedAndScheduled = false;
        for(int i = 1 ;  i <= 10 ;i++ ){
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
    @org.junit.jupiter.api.AfterEach
    void tearDown() {
    }
}