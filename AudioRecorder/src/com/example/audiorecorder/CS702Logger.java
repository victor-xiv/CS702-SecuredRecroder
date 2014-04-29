package com.example.audiorecorder;

import java.io.File;

import org.apache.log4j.FileAppender;
import org.apache.log4j.Logger;
import org.apache.log4j.PatternLayout;

/**
 * Call {@link #configure()}} from your application's activity.
 */
public class CS702Logger {
	
	
    public static void configure(String filename) {
		// set appender (our customized appender)
		FileAppender temp = new FileAppender();
		temp.setName("Root");

		
		temp.setFile(filename);
		temp.setImmediateFlush(true);
		temp.setAppend(true);
		
		// set layout (our customized PatternLayout)
		PatternLayout ly = new PatternLayout("Root");
		ly.setConversionPattern("%d{dd/MMM/yyyy-HH:mm:ss.SSS} %m%n");
		temp.setLayout(ly);
		temp.activateOptions();
		// set up logger
		Logger.getRootLogger().addAppender(temp);
		
    }
    
    public static void secureLog(String message){
    	Logger logger = Logger.getRootLogger();
    	logger.log(SecurityLevel.SECURITY, message);
    }
    
}