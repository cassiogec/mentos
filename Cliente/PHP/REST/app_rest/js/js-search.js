/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


$(document).on('click', 'a#btn_search', function() 
{    
        var placa = $('#ipt_search').val();
        
        if(placa !="") 
        {
            var parameters = {
               method : 'GET',
                 data : null,
          contentType : null,
                  url : 'get/consultar-veiculos-placa/'+placa
            };

            $('#table_results').empty();
            requestService(parameters)
                .done(function(ret) 
                {
                    $.each(ret, function(key, value) 
                    {   
                        value['tipo'] = setaTipo(value['tipo']);   
                    });
                    $('#listaFrota').tmpl(ret).appendTo("#table_results");
                })
                .fail(function (ret) 
                {
                    console.log('não, não deu');
                });
        }
});

