function getRemote() {
	
    var jsonString = $.ajax({
        type: "POST",
        dataType: "json",
        url: "${url}",
        data: {
            'updateF': 'true'
        },
        async: false
    }).responseText;
    
    return  $.parseJSON(jsonString);
}



setMap = function() {

	var data = getRemote();
	
	map = L.map('map');
	
	L.tileLayer('http://{s}.tile.cloudmade.com/BC9A493B41014CAABB98F0471D759707/997/256/{z}/{x}/{y}.png', {
		maxZoom: 18,
		attribution: 'Map data &copy; <a href="http://openstreetmap.org">OpenStreetMap</a> contributors, <a href="http://creativecommons.org/licenses/by-sa/2.0/">CC-BY-SA</a>, Imagery © <a href="http://cloudmade.com">CloudMade</a>'
	}).addTo(map);	
	
	map.setView([-32.83813, -50.59853], 3);
	
	for ( var i = 0; i < data.length; i++) {
		L.marker([data[i].posicionMapaX, data[i].posicionMapaY]).addTo(map).bindPopup("Nombre: " + data[i].nombre);
	}
	
}


window.onload = function() {
	this.setMap();
}



