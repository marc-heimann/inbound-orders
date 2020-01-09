package com.swisslog.inboundorders.eventbroker;

import java.io.Serializable;

import io.vertx.core.json.JsonObject;

public class BusinessFact implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	Fact fact;
	
	String id;
	
	public BusinessFact(Fact fact, String id) {
		this.setFact(fact);
		this.setId(id);
	}
	
	public Fact getFact() {
		return fact;
	}

	public void setFact(Fact fact) {
		this.fact = fact;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}	
	
	public enum Fact {
		NEW_TEST_EXECUTED, INBOUND_ORDER_CREATED, INBOUND_ORDER_DELETED
	}

	public String toJSON() {
		JsonObject jo = new JsonObject();
		jo.put("NAME", this.getFact().name());
		jo.put("REF_ID", this.getId());
		return jo.toString();
	}
	
}
