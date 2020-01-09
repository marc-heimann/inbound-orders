package com.swisslog.inboundorders.repo;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

import javax.enterprise.context.ApplicationScoped;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.swisslog.inboundorders.InboundOrder;
import com.swisslog.inboundorders.InboundOrder.OutboundOrderStatus;

@ApplicationScoped
public class InboundOrderManager {
	
	private ConcurrentHashMap<String, InboundOrder> inboundOrders = new ConcurrentHashMap<String, InboundOrder>();
	
	private DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMM");
	
	private AtomicInteger idGenerator = new AtomicInteger(0);	
	
	Logger logger = LogManager.getLogger("CONSOLE_JSON_APPENDER");
	
	private String getNextId() {
        String date = LocalDate.now().format(formatter);
        return String.format("%04d-%s", idGenerator.incrementAndGet(), date);
    }
			
	public InboundOrderManager() {		
		createSomeInboundOrders();		
	}
	
	public String add(InboundOrder oo) {
        String id = getNextId();
        oo.setId(id);
        inboundOrders.put(id, oo);
        return id;
    }

    public InboundOrder get(String id) {
    	InboundOrder io = inboundOrders.get(id);
        return io;
    }

    public List<InboundOrder> getAll() {
        List<InboundOrder> orders = new ArrayList<InboundOrder>();
        orders.addAll(inboundOrders.values());  
        
        return orders;
    }
    
    public InboundOrder delete(String id) {
    	return inboundOrders.remove(id);
    }
    
    private void createSomeInboundOrders() {
    	
    	if (inboundOrders.size() > 0)
    		return;
    	
    	int newRangeStart = (int) System.currentTimeMillis()/1000;
    	for (int i = newRangeStart; i< newRangeStart+100; i++) {
    		InboundOrder io = new InboundOrder();
    		io.setId("i");
    		io.setOrderType(Thread.currentThread().toString());
    		io.setPurchaseOrderId("P" + i + "DE" + newRangeStart);
    		io.setReceiptTime(System.currentTimeMillis());
    		io.setReplenishmentOrderId("-");
    		io.setSalesOrderId("SAL" + newRangeStart + i);
    		io.setShipmentTime(System.currentTimeMillis()-86400000);
    		io.setShowSuggestedOrderLines(false);
    		io.setStatus(OutboundOrderStatus.NOT_SENT);
    		io.setSupplierId(i+"-2019");
    		io.setSupplierName(Thread.currentThread().getName());
    		
    		logger.debug(io);
    		
    		this.add(io);
    	}
    }

	public InboundOrder update(InboundOrder io) {
		
		logger.debug("InboundOrderManager.update: " + io);
		
		if ( inboundOrders.containsKey( (io.getId()) ) ) 
		{			
			logger.debug("InboundOrderManager.update: Removed inboundorder: " + inboundOrders.remove(io.getId()));
			logger.debug("InboundOrderManager.update: Added inboundorder: " + inboundOrders.put(io.getId(), io));
			return io;
		} 
				
		return null;
	}

	public InboundOrder remove(String id) {
		
		InboundOrder io = inboundOrders.remove(id);
		
		logger.debug("InboundOrderManager.remove: " + io);
		
		return io;
		
	}
}
