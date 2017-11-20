$( document ).ready(function() {
	$(document.body).on('click','.book',function(e){
		e.preventDefault();
        var bookHotelName = $(this).prev().text();
        var bookHotelId = $(this).next().text();
        var hotelBooked = $(this).parent();

        if($.trim($("#sessionUser").html())==""){
            alert("Please log in or sign up to book a hotel!");
            $("#hiddenButton").click();
        }
        else{
            var bookInfo = {};
            bookInfo["hotelName"] = bookHotelName;
            bookInfo["hotelId"] = bookHotelId;
            var bookData = JSON.stringify(bookInfo);
            $(this).text("BOOKED!");
            bookingAjaxSubmit();

            function bookingAjaxSubmit(){
            	$.ajax({
					type: "POST",
					url: "/index/makeBooking",
					contentType: "application/json",
					dataType: "json",
					data: bookData,
					success: function (result) {
                        hotelBooked.fadeOut(1000);
                        hotelBooked.remove();
					},
					error: function (e) {
                        alert("Something went wrong, try again later.");
					}
				});
			}
        }
	});
	
	
	
	var myButton = $('#findHotel');
	var hotelSearchText = $('#hotelSearchBar');
	var hotels = [];
	myButton.on('click',function(e){
		e.preventDefault();
		var lat = '';
	    var lng = '';
	    var geocoder = new google.maps.Geocoder();
	    var zip = hotelSearchText.val().toString();
	    geocoder.geocode( { 'address': zip}, function(results, status) {
	      if (status == google.maps.GeocoderStatus.OK) {
	         lat = results[0].geometry.location.lat();
	         lng = results[0].geometry.location.lng();
	         var latLng = {lat: lat, lng: lng};
	         
	         var map = new google.maps.Map(document.getElementById('map'), {
	             center: latLng,
	             zoom: 15
	           });
	         
	         var searchParams = {location: latLng, radius: 7500, type: ['hotel']};
	         var hotelSearch = new google.maps.places.PlacesService(map);
	         hotelSearch.nearbySearch(searchParams, displayHotels);
	      } 
	      else {
	        alert("Geocode was not successful for the following reason: " + status);
	      }
	    });	    
	});
	
	
	
	function displayHotels(results, status){
		if(status== google.maps.places.PlacesServiceStatus.OK){
			hotels = [];
			for(var i = 1; i < 11; i++){				
				hotels.push(results[i]); 
			}
			$('#topTenHotels').empty();
			for(var x in hotels){
				var hotelName = hotels[x].name;
				var hotelPlaceId = hotels[x].place_id;
				$('#topTenHotels').append($('<div class ="row">'));
				$('#topTenHotels').children().eq(x).append($('<p class ="col-xs-10 col-sm-11 hotelName">').text(hotelName));
				$('#topTenHotels').children().eq(x).append($('<button class="col-xs-2 col-sm-1 btn btn-primary btn-sm book">Book</button>'));
				$('#topTenHotels').children().eq(x).append($('<p class = "hotelId">').text(hotelPlaceId));
			}
		}
	}
});


