$(document).ready(function() {
	$(".delete").on("click", function(e){
		e.preventDefault();
		var hotelId = $(this).prev().prev().text();
		var bookingData = $(this).parent();
		deleteAjaxSubmit();

		function deleteAjaxSubmit(){
			$.ajax({
				type: "DELETE",
				url: "/bookings/delete/" + hotelId,
				contentType: "application/json",
			    dataType : "json",
			    success: function (result) {
			           bookingData.fadeOut(1000);
			           bookingData.remove();
			    },
			    error: function (e) {
			    		alert("Something went wrong, try again later."); 
			    }
			});
		} 
	});
	
	$(".view").on("click", function(){
		var placeId = $(this).prev().html();
		var map;
		var infowindow;
		var latLng;
	    var geocoder = new google.maps.Geocoder();
	    google.maps.visualRefresh = true;
        $('#myModal').modal({show: true});
		initialize();
		
		function initialize(){
		    geocoder.geocode( {'placeId': placeId}, function(results, status) {
			      if (status == google.maps.GeocoderStatus.OK) {
			         var lat = results[0].geometry.location.lat();
			         var lng = results[0].geometry.location.lng();
			         latLng = {lat: lat, lng: lng};
					  	
			         var mapOptions = {
						    zoom: 12,
						 	anchor: latLng,
						    center: latLng,
			         		mapTypeId: google.maps.MapTypeId.ROADMAP
					   	};
					  	map = new google.maps.Map(document.getElementById('modalBody'), mapOptions);

					  	var service = new google.maps.places.PlacesService(map);
					    service.getDetails({
					        placeId: placeId
					      }, function(result, status) {
					        if (status != google.maps.places.PlacesServiceStatus.OK) {
					          alert(status);
					          return;
					        }
					        var marker = new google.maps.Marker({
					          map: map,
					          position: latLng
					        });
					        var address = result.adr_address;
					        var newAddr = address.split("</span>,");

                            infoWindow = new google.maps.InfoWindow({
                                content:result.name + "<br>" + newAddr[0] + "<br>" + newAddr[1] + "<br>" + newAddr[2],
                                disableAutoPan: false
                            });

					        google.maps.event.addListenerOnce(map, 'tilesloaded', function(){
                                map.panTo(latLng);
                                infoWindow.open(map, marker);
							});
			      		});
			      } 
			      else {
			        alert("Geocode was not successful for the following reason: " + status);
			      }
			    });	
		}
		$("#myModal").on("shown.bs.modal", function () {
		    $(".modal .modal-body").css('max-width', $(window).width()*0.95);
		});
		$("#myModal").on("shown.bs.modal", function () {
		    google.maps.event.trigger(map, "resize");
		});
	});
});