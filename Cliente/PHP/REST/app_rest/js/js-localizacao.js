/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

$(document).on('click' ,'a#dsPlaca', function() 
{
    var id = $(this).attr('dt_id');
    
    $('a#lista').attr('dt_id', id);
    $('a#mapa').attr('dt_id', id);
    
});

$(document).on('click', 'a#lista', function() {
        console.log('lista');
        
});
    



