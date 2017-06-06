/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function DialogMsg(title,msg, clas) {
    
    html = "<strong id='msg'>"+title+"</strong> "+msg+""; 
    $('#dialog_alert').hide(300);
    $('#dialog_msg').empty();
    
    $('#dialog_alert').removeClass('alert-info');
    $('#dialog_alert').removeClass('alert-waring');
    $('#dialog_alert').removeClass('alert-primary');
    $('#dialog_alert').removeClass('alert-success');
    
    $('#dialog_alert').addClass(clas);
    
    $('#dialog_msg').append(html);
    $('#dialog_alert').show(400);
    setTimeout(function(){
     $('#dialog_alert').hide(400);
}, 2000);
}

$(document).on('click', 'a#alterar', function() {
    $('#modal_title').text("Manutenção de Veículos");
    $('a#inserir').attr('dt_id', $(this).attr('dt_id'));
    $('a#inserir').attr('dt_fruit', 'alterar');
    
     var parameters = {
        'method' : 'GET',
        'data'   : null,
        'url'    : 'get/consultar-veiculo/'+$(this).attr('dt_id')
    };
 
    requestService(parameters)
        .done(function(data) 
        {
            $('#iptPlaca').val(data['placa']);
            $('#iptCapacidade').val(data['capacidade']);
            $('#iptTipo').val(data['tipo']);
            $('#drop_menu[dt_id="'+data['tipo']+'"]').click();
            $('#iptUnidade').val(data['uncapac']);  
        })
        .fail(function (ret) 
        {
            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
        });
    
});

$(document).on('click', 'a#btn_insert', function() {
    $('#modal_title').text("Cadastro de Veículos");
    $('a#inserir').attr('dt_fruit', 'inserir');
    limpaFormulario();
});

$(document).ready(function() 
{    
    var parameters = {
        'method' : 'GET',
        'data'   : null,
        'url'    : 'get/consultar-veiculos'
    };
 
    requestService(parameters)
        .done(function(data) 
        {
            $.each(data, function(key, value) 
            {   
                value['tipo'] = setaTipo(value['tipo']);   
            });
            
            $('#listaFrota').tmpl(data).appendTo("#table_results");   
        })
        .fail(function (ret) 
        {
            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
        });
});