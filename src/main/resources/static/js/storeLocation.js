	var geocoder;
	var map;
	var address;
	var marker;
	var StoreAddress="2855 Stevens Creek Blvd, Santa Clara, CA 95050"

	function placeMarker(location,map) {
		 if ( marker ) {
		 	marker.setPosition(location);
		 } else {
			 marker = new google.maps.Marker({
				 position: location,
				 map: map
		 	});
		 }
	 }

	function initialize() {
		 geocoder = new google.maps.Geocoder();
		
		 address = StoreAddress
		 geocoder.geocode( { 'address': address}, function(results, status) {
			 if (status == google.maps.GeocoderStatus.OK) {
				 map.setCenter(results[0].geometry.location);
				 placeMarker(results[0].geometry.location, map);
				 //latlng = new google.maps.LatLng(results[0].geometry.location.lat(), results[0].geometry.location.lng());		
			 } else {
			 	alert('Geocode was not successful for the following reason: ' + status);
			 }
	 	});

		 var mapOptions = {
		 zoom: 12,
		 //center: latlng
		 }
		 map = new google.maps.Map(document.getElementById('map-canvas'), mapOptions);
	}

	google.maps.event.addDomListener(window, 'load', initialize);