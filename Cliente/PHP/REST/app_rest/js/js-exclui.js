/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click', 'a#deletar', function() 
{
    $('#iptDel').val($(this).attr('dt_id'));
    
});

$(document).on('click', 'a#confirmar', function() 
{
    var id = $('#iptDel').val();
    
    var parameters = {
        'method' : 'GET',
        'url'    : 'delete/excluir-veiculo/'+id
    };
   
    requestService(parameters)
        .done(function(ret) 
        {
            if(ret['true']) {
                /* Impossibilita a ação durante o perído de exclusão */
                $('a#dsPlaca[dt_id="'+id+'"]').attr('dt_id', null);
                $('a#alterar[dt_id="'+id+'"]').attr('dt_id', null);
                $('a#deletar[dt_id="'+id+'"]').attr('dt_id', null);

                $('tr[dt_id="'+id+'"]').addClass('danger');
                setTimeout(function(){
                    $('tr[dt_id="'+id+'"]').remove();
                }, 2000);

                DialogMsg('Excluido', ret['msg'], 'alert-success');
            } else {
                DialogMsg('Ops!', ret['msg'], 'alert-warning');
            }
        })
        .fail(function (ret) 
        {
            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
        });
});