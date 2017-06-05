/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

//jQuery(function($){
//  $('#iptPlaca').mask("aaa - 9999"); 
//});

function setaTipo(tipo) 
{
    if(tipo == 1) {
       tipo = 'Carro';
    }
    if(tipo == 2) {
       tipo = 'Caminhão';
    }
    if(tipo == 3) {
       tipo = 'Camihão Carreta';
    }
    if(tipo == 4) {
       tipo = 'Caminhão Bitrem';
    }
    if(tipo == 5) {
       tipo = 'Caminhão Treminhão';
    }
    if(tipo == 6) {
       tipo = 'Moto';
    }
    if(tipo == 7) {
       tipo = 'Triciclo';
    }
    if(tipo == 8) {
       tipo = 'Quadriciclo';
    }
    return tipo;
}

function limpaFormulario() 
{
    $('#iptPlaca').val("");
    $('#iptCapacidade').val("");
    $('#iptTipo').val("");
    $('#iptUnidade').val("");
    $('#drop_title').text("Selecione o Tipo");
    
    
}

function printAlert(res) {
    if(res == true) {
        $('#alert').removeClass('label-waring');
        $('#alert').addClass('label-success');
        $('#alert').text("");
        $('#alert').text("Adicionado com sucesso");
    }else {
        $('#alert').removeClass('label-success');
        $('#alert').addClass('label-waring');
        $('#alert').text("");
        $('#alert').text("Insira todos os dados");
    }
}

$(document).on('click', 'a#drop_menu', function() {
    var tipo = $(this).attr('dt_id');
    $('#iptTipo').val(tipo);
    $('a#drop_title').text($(this).text());
});

$(document).on('click', 'a#inserir', function() 
{    
    var controller = $(this).attr('dt_fruit');
    
    var placa      = $('#iptPlaca').val();
    var capacidade = $('#iptCapacidade').val();
    var tipo       = $('#iptTipo').val();
    var unidade    = $('#iptUnidade').val();
    
    if(placa != "" || capacidade != "" || tipo != "") 
    {    
     
        if(controller=='inserir') {
            var veiculo = {
                dsPlaca      : placa,
                idTipo       : tipo,
                vlCapacidade : capacidade,
                dsUnidade    : unidade
            };

            var parameters = {
                method : 'POST',
                data   : JSON.stringify(veiculo),
           contentType : "application/json",
                url    : 'post/incluir-veiculo/',
            };
        
            console.log(parameters);

            var new_veiculo = 
            " <tr dt_id='10'>                  "
            + "        <th scope='row'></th>   "
            + "        <td dt_id='10' id='dsPlaca'>"+veiculo.dsPlaca+"</td>       "
            + "        <td dt_id='10' id='dsTipo'>"+veiculo.idTipo+"</td>        "
            + "        <td dt_id='10' id='dsCapacidade'> "+veiculo.vlCapacidade+"</td>  "
            + "        <td dt_id='10' id='dsUnidade'>"+veiculo.dsUnidade+"</td>     "
            + "       <td>"
            + "            <a id='alterar' dt_id='10' style='margin:0px;' data-toggle='modal' data-target='#insert-dialog' class='btn btn-block btn-success'>Alterar</a> "
            + "        </td>"
            + "        <td>"
            + "            <a id='deletar' dt_id='10' style='margin:0px;' data-toggle='modal' data-target='#delete-dialog' class='btn btn-block btn-danger'>Excluir</a> "
            + "        </td>"
            + "    </tr>     ";
       
            $.ajax({
                crossDomain : true, 
                url         : BASE+parameters.url,
                method      : parameters.method,
                contentType : parameters.contentType,
                   dataType : "json",
                data        : parameters.data,
                success     : function (result) {
                
                },
                error       : function (error) { error }
            });
        }
        
        if(controller == 'alterar') {
            var id = $(this).attr('dt_id');
            console.log('alterar: '+id);
            var veiculo = {
                cdVeiculo    : id,
                dsPlaca      : placa,
                idTipo       : tipo,
                vlCapacidade : capacidade,
                dsUnidade    : unidade
            };

            var parameters = {
                method : 'PUT',
                data   : JSON.stringify(veiculo),
           contentType : "application/json",
                url    : 'put/alterar-veiculo/',
            };
            
            $.ajax({
                crossDomain : true, 
                url         : BASE+parameters.url,
                method      : parameters.method,
                contentType : parameters.contentType,
                   dataType : "json",
                data        : parameters.data,
                success     : function (result) 
                {     
                    $('a#dsPlaca[dt_id="'+id+'"]').text(veiculo.dsPlaca);
                    $('td#idTipo[dt_id="'+id+'"]').text(setaTipo(veiculo.idTipo));
                    $('td#vlCapacidade[dt_id="'+id+'"]').text(veiculo.vlCapacidade);
                    $('td#dsUnidade[dt_id="'+id+'"]').text(veiculo.dsUnidade);
                    
                    $('button.close').click();
                    limpaFormulario();
                },
                error       : function (error) {
                
                }
            });
        }
    }else {
        printAlert(false);
    }    
});