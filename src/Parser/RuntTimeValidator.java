package Parser;
import Exception.RepeateIdException;

public interface RuntTimeValidator {

    public String[] getUserInput();

    public boolean isValidParameters(String[] input);

    public boolean validateSingleExpectedInterval(String singleInterval);

    public boolean validateSchedulingFrequency(String frequencyInterval);

    public boolean validateJobId(String jobId)throws RepeateIdException;

    public boolean validateJobFunction();

}
