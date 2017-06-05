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
            printAlert(true);
            
            $('tr[dt_id="'+id+'"]').remove();
        })
        .fail(function (ret) 
        {
            console.log('não, não deu');
        });
});