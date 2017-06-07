/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function initMap(pontos, lat_center, lng_center)
{
    var map = new google.maps.Map(document.getElementById('map'), {
        zoom: 15,
        center: {lat: parseFloat(lat_center), lng: parseFloat(lng_center)},
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

$(document).on('click', 'a#btn_search', function() 
{
    var placa = $('#iptPlaca').val();
    var data  = $('#iptData').val();
    var hora  = $('#iptHora').val();
    placa = placa.replace('-', '');
    if(placa!= "") 
    {    
            var parameters = {
               method : 'GET',
                 data : null,
          contentType : null,
                  url : 'get/consultar-veiculos-placa/'+placa
            };
    }else {
        DialogMsg('Ops', 'Faltou informar a placa', 'alert-warning');
    }
        requestService(parameters)
            .done(function(ret1) 
            {
                if(ret1['ret']=='true') 
                {
                    var id = ret1['objeto']['codigo'];
                    
                    var from = data.split("/");
                    var nova_data = from[2]+'-'+from[1]+'-'+from[0]+'T'+hora+':00.000-03:00';

                    var dados = {
                        cdVeiculo     : id,
                        dtLocalizacao : nova_data
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
                            if(!$.isEmptyObject(ret))
                            {
                                /* latitude e longitude inicial para o centro */
                                var lat_center =  ret[0]['latitude'];
                                var lng_center =  ret[0]['longitude'];

                                var pontos = [];

                                $.each(ret, function(key, value) {
                                    var ponto = {'lat':value['latitude'], 'lng': value['longitude']};
                                    pontos.push(ponto);
                                });

                                initMap(pontos, lat_center, lng_center);
                            } else {
                                DialogMsg('Não encontramos', 'Não há rota para este veículo', 'alert-info');
                            }
                        })
                        .fail(function (ret) 
                        {
                            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
                        });
                }else {
                    DialogMsg('Não encontrado', ret1['msg'], 'alert-warning');
                }
            })
            .fail(function (ret) 
            {
                 DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
            });
    
});

$(document).ready(function() 
{       
    $('#iptPlaca').mask("aaa-9999");
    $('#iptData').mask("99/99/9999");
    $('#iptHora').mask("99:99");
    
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
            $('#dialog_alerta').hide(600);
            if(!$.isEmptyObject(ret))
            {
                /* latitude e longitude inicial para o centro */
                var lat_center =  ret[0]['latitude'];
                var lng_center =  ret[0]['longitude'];

                var pontos = [];

                $.each(ret, function(key, value) {
                    var ponto = {'lat':value['latitude'], 'lng': value['longitude']};
                    pontos.push(ponto);
                });

                initMap(pontos, lat_center, lng_center);
            } else {
                DialogMsg('Não encontramos', 'Não há rota para este veículo', 'alert-info');
            }
        })
        .fail(function (ret) 
        {
            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
        });
});
