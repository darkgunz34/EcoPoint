let map;

function initMap() {

    // Map option
    var option ={
        zoom: 12,
        center: { lat: 43.6, lng: 3.8833 }
    }
    // New map
    map = new google.maps.Map(document.getElementById("map"),option);

    var marker_1 = new google.maps.Marker({
        position:{lat: 43.63, lng: 3.87 },
        map:map
    });

    var marker_2 = new google.maps.Marker({
        position:{lat: 43.6047, lng: 3.8807 },
        map:map
    });

    var marker_3 = new google.maps.Marker({
        position:{lat: 43.6068285, lng: 3.9233238 },
        map:map
    });

    var marker_4 = new google.maps.Marker({
        position:{lat: 43.5950812, lng: 3.8793785 },
        map:map
    });

    var marker_5 = new google.maps.Marker({
        position:{lat: 43.5722481, lng: 3.8537152 },
        map:map
    });

}