var searchIOId = document.getElementById('ioId');
var searchROId = document.getElementById('replenishmentOrderId');
var searchSOId = document.getElementById('salesOrderId');
var searchPOId = document.getElementById('purchaseOrderId');
var searchSupId = document.getElementById('supId');
var searchSupName = document.getElementById('supName');
var searchOrderType = document.getElementById('orderType');
var searchStatus = document.getElementById('status');

var button = document.getElementById('reloadButton');
var label = document.getElementById('errorLabel');
var protocol = window.location.protocol;
var requestUrl = window.location.host;
var apiVersion = "/v1";
var baseUrl = protocol + '//' + requestUrl + apiVersion +'/inboundorders'; //for server use
//var baseUrl = 'http://localhost:59821/v1/inboundorders'; //for local use
var IOId;

let elementsArray = [
	searchIOId, 
	searchROId, 
	searchSOId,
	searchPOId,
	searchSupId, 
	searchSupName, 
	searchOrderType,
	searchStatus
];
let elementsValueArray = [];

function buildHeader(table){
	let firstHeaderItems = ['Inbound Order ID','orderType','Purchase Order ID','Receipt Date','Replenishment Order ID','Sales Order ID','Shipping Date','showSuggestedOrderLines','Status','Supplier'];
	let secondHeaderItems = ['','','','','','','','','','ID','Name'];
	let searchHeaderItems = [
		'<input class="searchInput" type="number" name="ioId" id="ioId" style="width: 50px;">',
		'<input class="searchInput" type="text" name="orderType" id="orderType" style="width: 100px;">',
		'<input class="searchInput" type="number" name="purchaseOrderId" id="purchaseOrderId" style="width: 50px;">',
		'',
		'<input class="searchInput" type="number" name="replenishmentOrderId" id="replenishmentOrderId" style="width: 50px;">',
		'<input class="searchInput" type="number" name="salesOrderId" id="salesOrderId" style="width: 50px;">',
		'',
		'',
		'<input class="searchInput" type="text" name="status" id="status" style="width: 100px;">',
		'<input type="number" name="supId" id="supId" style="width: 50px;">',
		'<input type="text" name="supName" id="supName" style="width: 100px;">'
	];
	let tr = document.createElement('tr');
	let th;
	//build first header
	for(var i = 0; i < firstHeaderItems.length; i++){
		th = document.createElement('th');
		th.appendChild(document.createTextNode(firstHeaderItems[i]));
		if(firstHeaderItems[i] == 'Supplier'){
			th.setAttribute('colspan', 2);
		}
		else{
			th.setAttribute('colspan', 1);
		}
		tr.appendChild(th);
	}
	table.appendChild(tr);
	
	tr = document.createElement('tr');
	//build second header
	for(var i = 0; i < secondHeaderItems.length; i++){
		th = document.createElement('th');
		th.appendChild(document.createTextNode(secondHeaderItems[i]));
		tr.appendChild(th);
	}
	table.appendChild(tr);
	
	tr = document.createElement('tr');
	//build second header
	for(var i = 0; i < secondHeaderItems.length; i++){
		th = document.createElement('th');
		th.innerHTML=searchHeaderItems[i];
		tr.appendChild(th);
	}
	table.appendChild(tr);
	inputListeners();
	return table;
}

function unixToNormal(timestamp){
	let year = timestamp.getFullYear();
	let month = timestamp.getMonth()+1;
	let date = timestamp.getDate();
	let hour = timestamp.getHours();
	let min = "0" + timestamp.getMinutes();
	let sec = "0" + timestamp.getSeconds();
	let dateTime = date + '.' + month + '.' + year + ' ' + hour + ':' + min.substr(-2) + ':' + sec.substr(-2) ;
	return dateTime;
}

function buildTable(responseObj){
	var ios = JSON.parse(responseObj);
	var oldTable = document.getElementById('outputTable');
	newTable = buildHeader(oldTable.cloneNode(false));
	let tr;

	var td;
	
	for(let i = 0; i < ios.length; i++){
		let io = Object.entries(ios[i])
		tr = document.createElement('tr');
		for(let j = 0; j < io.length; j++){
			if(io[j][0] == '_links'){
				continue;
			}
			else{
				td = document.createElement('td');
				if(io[j][0] == 'supplier'){
					td.appendChild(document.createTextNode(io[j][1].id));
					tr.appendChild(td);
					td = document.createElement('td');
					td.appendChild(document.createTextNode(io[j][1].name));
				}
				else if(io[j][0] == 'shipmentTime' || io[j][0] == 'receiptTime'){
					let ioTime = unixToNormal(new Date(io[j][1]));
					td.appendChild(document.createTextNode(ioTime));
				}
				else{
					td.appendChild(document.createTextNode(io[j][1]));
				}
				
				tr.appendChild(td);
			}
			
		}
		newTable.appendChild(tr);
	}
	oldTable.parentNode.replaceChild(newTable, oldTable);
	filterTable();
}

function getSearchInputs(){
	elementsValueArray = [];
	elementsValueArray.push(document.getElementById('ioId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('replenishmentOrderId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('salesOrderId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('purchaseOrderId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('supId').value.toUpperCase());
	elementsValueArray.push(document.getElementById('supName').value.toUpperCase());
	elementsValueArray.push(''); //placeholder for shipping date
	elementsValueArray.push(''); //placeholder for receipt date
	elementsValueArray.push(''); //placeholder for show suggested lines
	elementsValueArray.push(document.getElementById('orderType').value.toUpperCase());
	elementsValueArray.push(document.getElementById('status').value.toUpperCase());
}

function filterTable() {
	  // Declare variables
	  var filter, table, tr, td, i, txtValue;
	  
	  table = document.getElementById('outputTable');
	  tr = table.getElementsByTagName("tr");
	  let filterEmpty = true;
	  for(let i = 0; i < elementsValueArray.length; i++){
		  filter = elementsValueArray[i];
		  if(filter != ""){
			  for (j = 0; j < tr.length; j++) {
				  filterEmpty = false;;
				  td = tr[j].getElementsByTagName("td")[i];
				  if (td) {
					  txtValue = td.textContent || td.innerText;
					  if (txtValue.toUpperCase().indexOf(filter) > -1) {
						  tr[j].style.display = "";
					  } 
					  else {
						  tr[j].style.display = "none";
					  }
				  }
			  }
		  }
		  else{
			  continue;
		  }
		  
	  }
	  if(filterEmpty){
		  for (j = 0; j < tr.length; j++) {
			  filterEmpty = false;
			  tr[j].style.display = "";
		  }
	  }
	  
	}

function httpGetAsync(theUrl, callback){
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.onreadystatechange = function() { 
        if (xmlHttp.readyState == 4 && xmlHttp.status == 200)
        	if(xmlHttp.response != ""){
        		callback(xmlHttp.response);
        	}
        	else{
        		label.textContent = "InboundOrder with id: "+IOId+" not available";
        	}
    }
    xmlHttp.open("GET", theUrl, true); // true for asynchronous 
    xmlHttp.send(null);
}

button.addEventListener("click", function(){
	httpGetAsync(baseUrl, buildTable);
	});

document.addEventListener("keyup", function() {
	getSearchInputs();
	filterTable();
});
function inputListeners(){
	
	searchIOId = document.getElementById('ioId');
	searchROId = document.getElementById('replenishmentOrderId');
	searchSOId = document.getElementById('salesOrderId');
	searchPOId = document.getElementById('purchaseOrderId');
	searchSupId = document.getElementById('supId');
	searchSupName = document.getElementById('supName');
	searchOrderType = document.getElementById('orderType');
	searchStatus = document.getElementById('status');
	
}



document.addEventListener("DOMContentLoaded", function(event) {
	httpGetAsync(baseUrl, buildTable);
});