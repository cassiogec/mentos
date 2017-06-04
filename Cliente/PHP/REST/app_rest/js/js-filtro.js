/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on('click', 'a#fil_tipo', function() {
    $('#title_drop_tipo').text($(this).text());
    var tipo = $(this).attr('dt_id');
    
    if(tipo == -1) {
        var parameters = {
           method : 'GET',
             data : tipo,
              url : 'get/listar-tipo/'
        };
        
        $('#table_results').empty();
        $.getJSON( "consulta_veiculos.json" , function( result ){
            console.log(result);
            cont = 0;
            $('#listaFrota').tmpl(result).appendTo("#table_results");
        });
        
    }else {
        var parameters = {
           method : 'GET',
             data : null,
              url : 'get/consultar-veiculos'
        };
        
        $('#table_results').empty();
        $.getJSON( "consulta_veiculos_tipo.json" , function( result ){
            console.log(result);
            cont = 0;
            $('#listaFrota').tmpl(result).appendTo("#table_results");
        });
    }
    
//    requestService(parameters)
//        .done(function(ret) 
//        {
//            a = 
//            console.log('oi, deu');
//        })
//        .fail(function (ret) 
//        {
//            console.log('não, não deu');
//        }); 

});