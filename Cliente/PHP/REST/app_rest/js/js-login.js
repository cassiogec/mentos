/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click', 'a#btn_logar', function() {
    
    var ip = $('#iptIp').val();
    window.sessionStorage.setItem('usuario', ip);

    // Depois, em outra página ou aba, recupera esse item
    var usuario = window.sessionStorage.getItem('usuario');

    console.log(usuario);
    window.location = 'home.html';
    // Remove o item
  //  window.sessionStorage.removeItem('usuario');
});
