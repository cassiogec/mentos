/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

var BASE = "http://10.1.1.3:8080/Servidor/webresources/mentos/";


/*
 *  Função generica para realizar requisições ao servidor 
 * 
 */

function requestService(parameters) 
{
    return $.ajax({
        url     : BASE+parameters.url,
        method  : parameters.method,
        crossDomain : true,
        type    : 'POST',
        dataType: 'json',
        data    : parameters.data,
        success : function (result) { },
        error   : function () { false }
    });
}

$(document).ready(function() 
{   
//    var veiculo = {
//        dsPlaca      : "ASD2323",
//        idTipo       : 2,
//        vlCapacidade : 50,
//        dsUnidade : "AS"
//    };
//    
    var parameters = {
        'method' : 'GET',
        'data'   : null,
        'url'    : 'get/consultar-veiculos'
    };
    
    requestService(parameters)
        .done(function(ret) 
        {
            console.log('oi, deu');
        })
        .fail(function (ret) 
        {
            console.log(' não deu');
        });
});