/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initMap(pontos, lat_center, lng_center)
{
    var map = new google.maps.Map(document.getElementById('map'), {
      zoom: 15,
      center: {lat: lat_center, lng: lng_center},
      mapTypeId: 'terrain'
    });

    var flightPath = new google.maps.Polyline({
      path: pontos,
      geodesic: true,
      strokeColor: '#FF0000',
      strokeOpacity: 1.0,
      strokeWeight: 4
    });

    flightPath.setMap(map);
}

$(document).ready(function() {
    
    
    var id = _GET('veiculo');
    
    var dados = {
                cdVeiculo     : id,
                dtLocalizacao : null
            };

            var parameters = {
                method : 'POST',
                data   : JSON.stringify(dados),
           contentType : "application/json",
                url    : 'post/localizacao/'
            };
    
    requestService(parameters)
        .done(function(ret) 
        {
            
                var lat_center = ret[0]['latitude'];
                var lng_center = ret[0]['longitude'];
                var pontos = [];
                
                $.each(ret, function(key, value) {
                    var ponto = {'lat':value['latitude'], 'lng': value['longitude']};
                    pontos.push(ponto);
                });
                
                initMap(pontos, lat_center, lng_center);
              
        })
        .fail(function (ret) 
        {
            console.log('não, não deu');
        });
    
});
