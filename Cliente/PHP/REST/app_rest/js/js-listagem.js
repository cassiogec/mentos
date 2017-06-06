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
    
    limpaFormulario(); /* limpa formulário antes de tentar requisitar servidor */
    
     var parameters = {
        'method' : 'GET',
        'data'   : null,
        'url'    : 'get/consultar-veiculo/'+$(this).attr('dt_id')
    };
 
    requestService(parameters)
        .done(function(data) 
        {
            
            if(data['ret']=='true'){
                $('#iptPlaca').val(data['objeto']['placa']);
                $('#iptCapacidade').val(data['objeto']['capacidade']);
                $('#iptTipo').val(data['objeto']['tipo']);
                $('#drop_menu[dt_id="'+data['objeto']['tipo']+'"]').click();
                $('#iptUnidade').val(data['objeto']['uncapac']);  
            }else {
                DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
            }
        })
        .fail(function (ret) 
        {
            DialogMsg('Ops', 'Parece que tem algo errado', 'alert-warning');
        });
    
});

$(document).on('click', 'a#btn_insert', function() {
    $('#modal_title').text("Cadastro de Veículos");
    $('a#inserir').attr('dt_fruit', 'inserir');
    
    limpaFormulario(); /* limpa formulário antes de tentar requisitar servidor */
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