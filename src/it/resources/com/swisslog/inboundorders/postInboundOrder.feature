Feature: The client creates a new Inbound Order by calling the swisslog REST API.

  Scenario Outline: client posts a new InboundOrder
    Given The client sends a new InboundOrder in JSON format '<ioJson>'
    When Do POST request
    Then The client receives a JSON response with the result of: '<resultJSON>'
    
    Examples:
    | ioJson 	| resultJSON	|		
		| " {'asnId':1001, 'purchaseOrderNumber': 2001, 'shipNoticeNumber': 3001, 'destinationLocation': { 'id':1, 'country':'CH', 'state':'', 'city': 'zurich', 'street': 'teststreet', 'number': '12b', 'zipCode': 'CH-56234' }, 'supplier': { 'id': 1, 'name': 'Superduper Supplier' }, 'shipDate': 1562501518, 'deliveryDate': 1562674318, 'skuIds': [0, 375, 668, 5, 6] } " | " { 'registrationStatus': 'CREATED', 'postedAsn': { 'asnId': 100, 'purchaseOrderNumber': 2001, 'shipNoticeNumber': 3001, 'destinationLocation': { 'country': 'CH', 'state': '', 'city': 'zurich', 'street': 'teststreet', 'zipCode': 'CH-56234', 'number': '12b' }, 'supplier': { 'id': 1, 'name': 'Superduper Supplier' }, 'shipDate': 1562501518, 'deliveryDate': 1562674318, 'skuIds': [ 0, 375, 668, 5, 6 ], 'links': [] } } " |   