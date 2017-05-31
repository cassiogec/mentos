/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() 
{   
    
    $.getJSON( "consulta_veiculos.json" , function( result ){
        console.log(result);
    });
    
    var parameters = {
        method : 'GET',
          data : null,
           url : 'get/consultar-veiculos'
    };
    
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

    $.getJSON( "consulta_veiculos.json" , function( result ){
        console.log(result);
        cont = 0;
        $('#listaFrota').tmpl(result).appendTo("#results");
    });

});

$(document).on('click', 'a#delete', function () {
    var v_id = $(this).attr('dt_v');
});


$(document).on('click', 'a#update', function () {
    var v_id = $(this).attr('dt_v');
});