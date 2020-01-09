package com.swisslog.inboundorders;

public class InboundOrder {
	String id;
	String replenishmentOrderId;
	String salesOrderId;
	String purchaseOrderId;
	String supplierId;
	String supplierName;
	long shipmentTime;
	long receiptTime;
	boolean showSuggestedOrderLines;
	String orderType;
	OutboundOrderStatus status; 
	
	public String getId() {
		return id;
	}



	public void setId(String id) {
		this.id = id;
	}



	public String getReplenishmentOrderId() {
		return replenishmentOrderId;
	}



	public void setReplenishmentOrderId(String replenishmentOrderId) {
		this.replenishmentOrderId = replenishmentOrderId;
	}



	public String getSalesOrderId() {
		return salesOrderId;
	}



	public void setSalesOrderId(String salesOrderId) {
		this.salesOrderId = salesOrderId;
	}



	public String getPurchaseOrderId() {
		return purchaseOrderId;
	}



	public void setPurchaseOrderId(String purchaseOrderId) {
		this.purchaseOrderId = purchaseOrderId;
	}



	public String getSupplierId() {
		return supplierId;
	}



	public void setSupplierId(String supplierId) {
		this.supplierId = supplierId;
	}



	public String getSupplierName() {
		return supplierName;
	}



	public void setSupplierName(String supplierName) {
		this.supplierName = supplierName;
	}



	public long getShipmentTime() {
		return shipmentTime;
	}



	public void setShipmentTime(long shipmentTime) {
		this.shipmentTime = shipmentTime;
	}



	public long getReceiptTime() {
		return receiptTime;
	}



	public void setReceiptTime(long receiptTime) {
		this.receiptTime = receiptTime;
	}



	public boolean isShowSuggestedOrderLines() {
		return showSuggestedOrderLines;
	}



	public void setShowSuggestedOrderLines(boolean showSuggestedOrderLines) {
		this.showSuggestedOrderLines = showSuggestedOrderLines;
	}



	public String getOrderType() {
		return orderType;
	}



	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}



	public OutboundOrderStatus getStatus() {
		return status;
	}



	public void setStatus(OutboundOrderStatus status) {
		this.status = status;
	}

	

	public enum OutboundOrderStatus {
		NOT_SENT, SENT_TO_PURCHASE_SYSTEM, REQUESTED, SENT_TO_ORDER_SYSTEM, CONFIRMED, PARTIALLY_SHIPPED, SHIPPED, PARTIALLY_RECEIVED, RECEIVED;
	}
	
}
