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
        $('#title_drop_tipo').text("Todos");
        var placa = $('#ipt_search').val();
        placa = placa.replace('-', '');
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
                    if(ret['ret']=='true') {
                        $.each(ret['objeto'], function(key, value) 
                        {   
                            value['tipo'] = setaTipo(value['tipo']);   
                        });
                        $('#listaFrota').tmpl(ret['objeto']).appendTo("#table_results");
                    }else {
                        DialogMsg('Não encontrado', 'não encontramos este registro', 'alert-info');
                    }
                })
                .fail(function (ret) 
                {
                     DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
                });
        }else {
            DialogMsg('Hmm..', 'Insira uma placa para pesquisar', 'alert-info');
        }
});

