package Logger;

import java.io.File;
import java.io.File.*;
import java.io.IOException;
import java.util.logging.*;

public class LoggerInstance {
    private static  LoggerInstance singleInst = null ;
    private final static Logger logger = Logger.getLogger(Logger.GLOBAL_LOGGER_NAME);

    private LoggerInstance(){
        setupHandler();
    }

    public static LoggerInstance getTheInstance(){
        if(singleInst == null){
            singleInst = new LoggerInstance();
        }
        return singleInst;
    }


    private void setupHandler(){
        try{
            FileHandler fileHandler = new FileHandler("logFile.log");
            fileHandler.setLevel(Level.INFO);
            logger.setUseParentHandlers(false);
            logger.addHandler(fileHandler);
        }catch(IOException e){
            logger.log(Level.SEVERE,"Log FIle not working.",e);
        }

    }

    public void logMessage(String message){
        logger.log(Level.INFO,message);
    }

    public void logException(String message,Exception e){
        logger.log(Level.SEVERE,message,e);
    }

}
