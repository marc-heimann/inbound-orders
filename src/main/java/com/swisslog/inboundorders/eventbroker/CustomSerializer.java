package com.swisslog.inboundorders.eventbroker;

import java.util.Map;

import org.apache.kafka.common.serialization.Serializer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.fasterxml.jackson.databind.ObjectMapper;
public class CustomSerializer implements Serializer<CustomObject> 
{
	
	Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	
    @Override
    public void configure(Map<String, ?> configs, boolean isKey) 
    {
    	
    }
    
    @Override
    public byte[] serialize(String topic, CustomObject data) 
    {
        byte[] retVal = null;
        ObjectMapper objectMapper = new ObjectMapper();
        try {
        retVal = objectMapper.writeValueAsString(data).getBytes();
        } catch (Exception exception) {        
        	logger.error("CustomSerializer.serialize: "+ exception.getMessage() +" -> " + data);
        }
        return retVal;
    }
    @Override
    public void close() {
    }
}