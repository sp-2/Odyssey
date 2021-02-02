function retrievePianos() {
	var url         = '/pianos/listAll/ajax/search';
    var current_url = window.location;
    var category    = current_url.href.split('/').pop();
    
    url = url + '?sortVal=' + $('#sortVal').val();
    if (category != 'listAll') {
        url = url + '&category=' + category;
    }

    url = url + '&make=' + $('#make').val();

    url = url + '&grand_upright=' + $('#grand_upright').val();

    if ($('#stock').is(":checked")) {
        url = url + '&in_stock=' + $('#stock').val();
    } else {
    	url = url + '&in_stock=' + "off"
    }

    $("#resultsBlock").load(url);
}

