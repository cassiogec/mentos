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
        crossDomain : true, 
        url         : BASE+parameters.url,
        method      : parameters.method,
        contentType : parameters.contentType,
        data        : parameters.data,
        success     : function (result) { result; },
        error       : function (error) { error }
    });
}
