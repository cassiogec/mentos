/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on('click', 'a#fil_tipo', function() 
{
    $('#title_drop_tipo').text($(this).text());
    
    var tipo = $(this).attr('dt_id');
    
    if(tipo == -1) {
        var parameters = {
           method : 'GET',
             data : null,
      contentType : null,
              url : 'get/consultar-veiculos/'
        };
        
        $('#table_results').empty();
        requestService(parameters)
            .done(function(ret) 
            {
                if($.isEmptyObject(ret)) {
                    DialogMsg('Não encontrado', 'acho que você não tem esse tipo de registro', 'alert-info');
                }
                $.each(ret, function(key, value) 
                {   
                    value['tipo'] = setaTipo(value['tipo']);   
                });
                $('#listaFrota').tmpl(ret).appendTo("#table_results");
            })
            .fail(function (ret) 
            {
                DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
            });
    } else {
        var parameters = {
           method : 'GET',
             data : null,
      contentType : null,
              url : 'get/consultar-veiculos-tipo/'+tipo
        };
        
        $('#table_results').empty();
        
        requestService(parameters)
            .done(function(ret) 
            {
                if($.isEmptyObject(ret)) {
                    DialogMsg('Não encontrado', 'acho que você não tem esse tipo de registro', 'alert-info');
                }else{
                    $.each(ret, function(key, value) 
                    {   
                        value['tipo'] = setaTipo(value['tipo']);   
                    });
                    $('#listaFrota').tmpl(ret).appendTo("#table_results");
                }
            })
            .fail(function (ret) 
            {
                DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
            });
    }
});