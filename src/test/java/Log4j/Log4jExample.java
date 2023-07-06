package Log4j;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.config.Configurator;
import org.apache.logging.log4j.core.config.builder.api.ConfigurationBuilder;
import org.apache.logging.log4j.core.config.builder.impl.BuiltConfiguration;
import org.apache.logging.log4j.core.config.builder.impl.DefaultConfigurationBuilder;


public class Log4jExample {

    //Log4j logging level is for logic code and not for test code. for test code log4j
    //requires configuration through a method. test files will not take log4j properties except from properties file
    // either create a configure class in test files or a properties file in test/resources.

    //create a constructor of Logger class and pass the class name that has to b logged.
    private static final Logger logger =  LogManager.getLogger(Log4jExample.class);

    public static void main(String[] args) {

        configureLog4j(); // require this config class to log in test files.

        logger.fatal("there is a fetal error");
        logger.trace("this is simple Trace message");
        logger.debug("debug the code here");
        logger.info("Here is some Message about the code in console/ terminal / compiler");
        logger.warn("here is a warning that this dependency will depreciate");
        logger.error("here is an occurrence of error which is handled by exception",new RuntimeException("some Exception"));
    }
    private static void configureLog4j() {
        ConfigurationBuilder<BuiltConfiguration> builder = new DefaultConfigurationBuilder<>();
        builder.setStatusLevel(org.apache.logging.log4j.Level.ALL);
        builder.setConfigurationName("ProgrammaticConfig");

        // Configure console appender
        builder.add(builder.newAppender("ConsoleAppender", "CONSOLE")
                .addAttribute("target", "SYSTEM_OUT")
                .add(builder.newLayout("PatternLayout")
                        .addAttribute("pattern", "%d{HH:mm:ss.SSS} [%t] %-5level %logger{36} - %msg%n")));

        // Create root logger
        builder.add(builder.newRootLogger(org.apache.logging.log4j.Level.ALL)
                .add(builder.newAppenderRef("ConsoleAppender")));

        // Build the configuration and initialize Log4j
        Configurator.initialize(builder.build());
    }

    //Log4j is java based logging library that provides flexible and configurable logging
    //framework. it is used in java applications to capture and manage log messages. in production application
    // there is no use of SYSTEM.OUT instead log is used.






}
