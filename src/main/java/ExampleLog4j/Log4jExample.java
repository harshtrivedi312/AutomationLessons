package ExampleLog4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

//logging from java logic side and not test files do not require config class it can take config either from
// XML file or a properties file from resources.

public class Log4jExample {
    private static final Logger logger =  LogManager.getLogger(Log4jExample.class);

    public static void main(String[] args) {

        logger.fatal("there is a fetal error");
        logger.trace("this is simple Trace message");
        logger.debug("debug the code here");
        logger.info("Here is some Message about the code in console/ terminal / compiler");
        logger.warn("here is a warning that this dependency will depreciate");
        logger.error("here is an occurrence of error which is handled by exception",new RuntimeException("some Exception"));

    }
}
