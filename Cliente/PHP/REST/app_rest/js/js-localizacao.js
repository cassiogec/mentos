/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click', 'a#abre_mapa', function() {
    var id = $(this).attr('dt_id');
    
    location.href = 'maps.html?veiculo='+id;
});

$(document).on('click' ,'a#dsPlaca', function() 
{
    var id = $(this).attr('dt_id');
    var placa = $(this).attr('dt_placa');
    
    $('a#listar').attr('dt_id', id);
    $('a#listar').attr('dt_placa', placa);
    
    $('a#lista').attr('dt_id', id);
    $('a#abre_mapa').attr('dt_id', id);
    
});

$(document).on('click', 'a#listar', function() 
{
//    var placa = $('#iptPlaca_coord').val();
    var id    = $(this).attr('dt_id');
    var data  = $('#iptData_coord').val();
    var hora  = $('#iptHora_coord').val();
    
    console.log(data);
    var from = data.split("/");
    var nova_data = from[2]+'-'+from[1]+'-'+from[0]+' '+hora+':00.000';
    
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
            console.log(ret);
  
            $('#t_location').show(400);
            $('#table_location').empty();
            $('#listaLocalizacao').tmpl(ret).appendTo("#table_location");
        })
        .fail(function (ret) 
        {
            console.log('não, não deu');
        });   
});
    



