/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


function DialogMsg(title,msg, clas) {
    
    var html = "<strong id='msg'>"+title+"</strong> "+msg+""; 
    $('#dialog_alerta').hide(300);
    $('#dialog_msg').empty();
    
    $('#dialog_alerta').removeClass('alert-info');
    $('#dialog_alerta').removeClass('alert-warning');
    $('#dialog_alerta').removeClass('alert-primary');
    $('#dialog_alerta').removeClass('alert-success');
    
    $('#dialog_alerta').addClass(clas);
    
    $('#dialog_msg').append(html);
    $('#dialog_alerta').show(400);
    setTimeout(function() {
        $('#dialog_alerta').hide(400);
}, 2000);
}