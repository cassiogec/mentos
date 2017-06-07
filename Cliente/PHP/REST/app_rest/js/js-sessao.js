/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).ready(function() {
    if(!window.sessionStorage.getItem('usuario')){
    window.location = 'index.html';
    }
});

$(document).on('click', '#sair', function() {
    window.sessionStorage.removeItem('usuario');
    window.location = 'index.html';
});
