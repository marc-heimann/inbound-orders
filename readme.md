# Inbound Order Service
This service provides REST endpoints to create, receive or delete resources.

Its concern is to deal with everything around the business process of inbound ordering (ordering goods for the warehouse)

## /inboundorder
The inboundorder endpoint supports the http methods POST, PUT, GET and DELETE

### 	HTTP method POST
Creates a new inboundorder object with the hand-over status of the inboundorder

	/inboundorder/{inboundorder}

### 	HTTP method PUT
Updates an existing inboundorder object with the hand-over status of the inboundorder

	/inboundorder/{inboundorder}

### 	HTTP method GET
Returns an inboundorder object referenced by its identifier
	
	/inboundorder/{id}

### 	HTTP method DELETE 
Removes an inboundorder object referenced by its identifier

	/inboundorder/{id}

## /inboundorders
The inboundorders endpoint supports the http method GET

### 	HTTP method GET 
Returns all inboundorders object referenced by its identifier

	/inboundorders	
	
## Installation 

###		Kubernetes Helm

To install the helm chart of the inbound-order-service, please execute the following command:

	$ helm install --namespace my-namespace -f inboundorders-values.yaml --name my-instance sl-application/inbound-order-service

### 	Kubeapps

To install the chart from Kubeapps, do the following steps:

	1. Search for "inbound-order" under the Catalog tab in the main menu.
	2. Click the one you want to be installed.
	3. Click the "Deploy" button at the upper right corner of the details view.