package com.swisslog.inboundorders.logging;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class LoggingUtils {

	Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	/*
	ConfigurationSource source = new ConfigurationSource();
	source.setLocation(logConfigurationFile);
	source.setFile(new File(logConfigurationFile));
	source.setInputStream(new FileInputStream(logConfigurationFile));
	Configurator.initialize(null, source);
	*/
	
	public LoggingUtils() {
		
	}


}
