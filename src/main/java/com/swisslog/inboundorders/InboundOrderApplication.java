package com.swisslog.inboundorders;

import javax.ws.rs.ApplicationPath;
import javax.ws.rs.core.Application;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.openapi.annotations.OpenAPIDefinition;
import org.eclipse.microprofile.openapi.annotations.info.Contact;
import org.eclipse.microprofile.openapi.annotations.info.Info;
import org.eclipse.microprofile.openapi.annotations.servers.Server;

/**
 * 
 * InboundOrder Service. JAX-RS
 * @author Marc Heimann (marc.heimann@swisslog.com)
 *
 */

@ApplicationPath("/")
@OpenAPIDefinition(info = @Info(
    title = "Inbound Order Service Application", 
    version = "0.0.1", 
    contact = @Contact(
            name = "Swisslog", 
            email = "marc.heimann@swisslog.com",
            url = "http://www.swisslog.com")
    ),
    servers = {
        @Server(url = "/v1/inboundorder",description = "localhost"),
        @Server(url = "/v1/inboundorders",description = "localhost")
    }
)
public class InboundOrderApplication extends Application {	
	
	static Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");	
	
	@Context
	private UriInfo info;
	
	public InboundOrderApplication() 
	{
		
		System.out.print("---> INBOUNDORDERS SERVICE STARTED <----");	
	}
	
	/*
	static void runConsumer() 
	{        
		Consumer<Long, String> consumer = ConsumerCreator.createConsumer();
		
        int noMessageFound = 0;
        
        while (true) 
        {
          ConsumerRecords<Long, String> consumerRecords = consumer.poll(1000);
          // 1000 is the time in milliseconds consumer will wait if no record is found at broker.
          if (consumerRecords.count() == 0) {
              noMessageFound++;
              if (noMessageFound > IKafkaConstants.MAX_NO_MESSAGE_FOUND_COUNT)
                // If no message found count is reached to threshold exit loop.  
                break;
              else
                  continue;
          }
          //print each record. 
          consumerRecords.forEach(record -> {
              System.out.println("Record Key " + record.key());
              System.out.println("Record value " + record.value());
              System.out.println("Record partition " + record.partition());
              System.out.println("Record offset " + record.offset());
           });
          // commits the offset of record to broker. 
           consumer.commitAsync();
        }
    consumer.close();
    }*/
	  	
}
