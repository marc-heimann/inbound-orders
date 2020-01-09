package com.swisslog.inboundorders.eventbroker;

import javax.inject.Singleton;

import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.clients.producer.RecordMetadata;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.tools.picocli.CommandLine.ExecutionException;

@Singleton
public class KafkaProducer {

	static Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	
	static final Producer<Long, String> producer = ProducerCreator.createProducer();
		
	public static void send(BusinessFact fact) throws Throwable
    {    	
    	
        ProducerRecord<Long, String> record = new ProducerRecord<>(IKafkaConstants.TOPIC_NAME,  fact.toJSON());
        
        try 
        {
        	
        	RecordMetadata metadata = producer.send(record).get();            
        	
            logger.debug("New object sent to cluster: " + metadata);
            
        } 
        catch (ExecutionException e) 
        {
        	
        	throw e;
                 
        } 
        catch (InterruptedException e) 
        {

        	throw e;
        	
        }
       
    }
	
}
