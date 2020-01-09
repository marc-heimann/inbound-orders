package com.swisslog.inboundorders.web;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriBuilder;
import javax.ws.rs.core.UriInfo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.eclipse.microprofile.opentracing.Traced;

import com.swisslog.inboundorders.InboundOrder;
import com.swisslog.inboundorders.eventbroker.BusinessFact;
import com.swisslog.inboundorders.eventbroker.BusinessFact.Fact;
import com.swisslog.inboundorders.eventbroker.KafkaProducer;
import com.swisslog.inboundorders.repo.InboundOrderManager;

@Path("/v1")
@RequestScoped
public class InboundOrderService {

    @Inject
    private InboundOrderManager orderManager;
    
	@Context
	private UriInfo info;

	Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");

	@POST
    @Path("inboundorder/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response add(InboundOrder io) {
        String orderId = orderManager.add(io);
        
        logger.log(Level.INFO, io);
        
       /* try {
			KafkaProducer.send(new BusinessFact(Fact.INBOUND_ORDER_CREATED, orderId));
		} catch (Throwable e) {
			logger.error(e.toString());
			e.printStackTrace();
		}*/
        
        return Response.created(
                UriBuilder.fromResource(this.getClass()).path("inboundorder/"+io.getId()).build(io))
                .build();
    }
	
	@PUT
    @Path("inboundorder/")
    @Consumes(MediaType.APPLICATION_JSON)
    public Response update(InboundOrder io) {
        InboundOrder sio = orderManager.update(io);
        
        if (sio == null)
        	return Response.noContent().build();
        
        logger.log(Level.INFO, sio);
        
        return Response.created(
                UriBuilder.fromResource(this.getClass()).path("inboundorder/"+sio.getId()).build())
                .build();
    }
			
    @GET
    @Path("inboundorder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getInboundOrder(@PathParam("id") String id) {
    	InboundOrder io = orderManager.get(id);
    	
    	if (io == null) 
    	{
    		logger.debug("InboundOrderService.getInboundOrder: Order with id " + id + " could not be found");
    		return Response.noContent().build();
    	}
    	
    	logger.info("InboundOrderService.getInboundOrder: " + io);
    	
        return Response.ok(io).build();
    } 

    @DELETE
    @Path("inboundorder/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Response deleteInboundOrder(@PathParam("id") String id) 
    {
    	
    	InboundOrder io = orderManager.remove(id);
    	
    	if (io == null)    	
    		return Response.noContent().build(); 	 
    	    	
    	logger.log(Level.INFO, io);
    	/*
    	try {
			KafkaProducer.send(new BusinessFact(Fact.INBOUND_ORDER_DELETED, io.getId()));
		} catch (Throwable e) {
			logger.error(e.toString());
			e.printStackTrace();
		}*/
    	
        return Response.ok(io).build();
        
    } 
    
    @GET
    @Path("inboundorders/")
    @Produces(MediaType.APPLICATION_JSON)
    public Response getAllInboundOrders() {
    	
    	List<InboundOrder> orders = orderManager.getAll();
    	
        return Response.ok(orders).build();
    }
    
    
}