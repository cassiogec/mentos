/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click', 'a#drop_menu', function() {
    var tipo = $(this).attr('dt_id');
    $('#iptTipo').val(tipo);
    $('a#drop_title').text($(this).text());
});

$(document).on('click', 'a#inserir', function() 
{    
    var placa = $('#iptPlaca').val();
    var capacidade = $('#iptCapacidade').val();
    var tipo = $('#iptTipo').val();
    var unidade = $('#iptUnidade').val();
    
    if(placa != "" || capacidade != "" || tipo != "") 
    {    
        var veiculo = {
            dsPlaca      : placa,
            idTipo       : tipo,
            vlCapacidade : capacidade,
            dsUnidade    : unidade
        };

        var parameters = {
            'method' : 'PUT',
            'data'   : veiculo,
            'url'    : '/get/adicionar-veiculo'
        };
        
        var new_veiculo = 
       " <tr dt_id='10'>           "
       + "        <th scope='row'></th>   "
       + "        <td>"+veiculo.dsPlaca+"</td>       "
       + "        <td>"+veiculo.idTipo+"</td>        "
       + "        <td>"+veiculo.vlCapacidade+"</td>  "
       + "        <td>"+veiculo.dsUnidade+"</td>     "
       + "       <td>"
       + "            <a id='alterar' dt_id='10' style='margin:0px;' data-toggle='modal' data-target='#insert-dialog' class='btn btn-block btn-success'>Alterar</a> "
       + "        </td>"
       + "        <td>"
       + "            <a id='deletar' dt_id='10' style='margin:0px;' data-toggle='modal' data-target='#delete-dialog' class='btn btn-block btn-danger'>Excluir</a> "
       + "        </td>"
       + "    </tr>     ";
        
        console.log(new_veiculo);
        
        $('#results').append(new_veiculo);
        
//        requestService(parameters)
//        .done(function(ret) 
//        {
//            printAlert(true);
//            
//        })
//        .fail(function (ret) 
//        {
//            console.log('não, não deu');
//        });
        
    }else {
        printAlert(false);
    }
    
});

function clearInputs() {
    $('iptPlaca').val("");
    $('iptTipo').val("");
    $('iptCapacidade').val("");
    $('iptUnidade').val();
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