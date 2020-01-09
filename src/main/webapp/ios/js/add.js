let submitButton = document.getElementById("addButton");
let orderType = document.getElementById("orderType");
let poID = document.getElementById("poID");
let roID = document.getElementById("roID");
let soID = document.getElementById("soID");
let ssOL = document.getElementById("ssOL");
let status = document.getElementById("status");
let supId = document.getElementById("supID");
let supName = document.getElementById("supName");
let shipDate = document.getElementById("shipDate");
let shipTime = document.getElementById("shipTime");
let recDate = document.getElementById("recDate");
let recTime = document.getElementById("recTime");
let errorLabel = document.getElementById("errorLabel");
var protocol = window.location.protocol;
var requestUrl = window.location.host;
var apiVersion = "/v1";
var url = protocol + '//' + requestUrl + apiVersion + '/inboundorder'; //for server use
//var url = "http://api.swisslog.net/v1/inboundorder";
let ioData;

function httpPost(theUrl, data, callback){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 201)
            callback(xmlHttp.getResponseHeader("location"));
    }
    xmlHttp.open("POST", theUrl, true); // true for asynchronous
    xmlHttp.setRequestHeader("Content-Type", "application/json");
    xmlHttp.setRequestHeader("Accept", "application/json");
    xmlHttp.send(data);
}
function defaultTime(){
	if(shipDate.value ==""){
		shipDate.value="1970-01-01";
	}
	if(recDate.value ==""){
		recDate.value="1970-01-01";
	}
	if(shipTime.value ==""){
		shipTime.value="00:01";
	}
	if(recTime.value ==""){
		recTime.value="00:01";
	}
}
submitButton.addEventListener("click", function(){
	defaultTime();
	let shipTimeStamp = ((shipDate.valueAsNumber/1000) + (shipTime.valueAsNumber/1000))*1000;
	let recTimeStamp = ((recDate.valueAsNumber/1000) + (recTime.valueAsNumber/1000))*1000;
	let io = {
			"orderType": orderType.value,
			"purchaseOrderId": poID.value,
			"receiptTime": recTimeStamp,
			"replenishmentOrderId": roID.value,
			"salesOrderId": soID.value,
			"shipmentTime": shipTimeStamp,
			"showSuggestedOrderLines": ssOL.checked,
			"status": status.value,
			"supplier": {
				"id": supId.value,
				"name": supName.value
			}
	}
	ioData = JSON.stringify(io);
	//asnData = asn;
	httpPost(url, ioData, logResponse);
});

function logResponse(data){
	errorLabel.textContent = "IO at location: "+data+" created successfully.";
	orderType.value = "";
	poID.value = "";
	roID.value = "";
	soID.value = "";
	ssOL.checked = false;
	status.value = "NOT_SENT";
	supId.value = "";
	supName.value = "";
	shipDate.value = "";
	shipTime.value = "";
	recDate.value = "";
	recTime.value = "";
}