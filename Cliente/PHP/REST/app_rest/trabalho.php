<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Mobile support -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
        
     <!-- Material Design fonts -->
  <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
  <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">

  <!-- Bootstrap -->
  <link href="css/boostrap.min.css" rel="stylesheet">

  <!-- Bootstrap Material Design -->
  <link href="dist/css/bootstrap-material-design.css" rel="stylesheet">
  <link href="dist/css/ripples.min.css" rel="stylesheet">

  <!-- Dropdown.js -->
  <link href="css/jquery.dropdown.css" rel="stylesheet">

  <!-- Page style -->
  <link href="index.css" rel="stylesheet">

  <!-- jQuery -->
  <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
  
  <link href="https://developers.google.com/maps/documentation/javascript/demos/demos.css" rel="stylesheet">
  
  <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
    </style>
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDNoLvKMx7BaqWCsQLi7XTe7V5fEDHISG4&callback=initMap" async defer></script>
        
        <script type="text/javascript">
            var map;
            function initMap() {
              map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: -28.6349384, lng: -53.0955204},
                zoom: 15
              });
            }
 
            $(document).ready(function() {
                altura = $(window).height();
                largura = $(window).width();
                
                console.log(largura);
                $('#box_fab_button').css({'left' : (largura - 84), 'top' : (altura - 84)});
            });
    </script>
    
    <script src="rest.js"> </script>
      
      <!-- Latest compiled and minified JavaScript -->
      <script src="js/boostrap.min.js" ></script>
    </head>
    <body>
        <div class="bs-component">
          <div style="font-weight: bold; background-color: #6495ED; box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);" class="navbar">
            <div class="container-fluid">
                <div class="container">
              <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-warning-collapse">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:void(0)">Brand</a>
              </div>
              <div class="navbar-collapse navbar-warning-collapse collapse in" aria-expanded="true">
                <ul class="nav navbar-nav">
                  <li class="dropdown">
                    <a href="" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Menu
                      <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="javascript:void(0)">Frota</a></li>
                      <li><a href="javascript:void(0)">Mapa em tempo real</a></li>
                      <li><a href="javascript:void(0)">Something else here</a></li>
                      <li class="divider"></li>
                      <li class="dropdown-header">Veja todas as empresas</li>
                      <li><a href="javascript:void(0)">Empresas cadastradas</a></li>
                    </ul>
                  </li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="">Daniel Matheus</a></li>
                    
                  <li class="dropdown">
                    <a href="" data-target="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">
                      <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="">Action</a></li>
                      <li><a href="">Another action</a></li>
                      <li><a href="">Something else here</a></li>
                      <li class="divider"></li>
                      <li><a href="">Sair</a></li>
                    </ul>
                  </li>
                </ul>
              </div>
          </div>
        </div>
        </div>
            
        <div class="container-fluid main">  
            <div class="col-md-10 col-md-offset-1">
                <div class="col-md-4">
                 <!--<h3>Olá, Daniel</h3>-->   
                </div>
            </div>
            <div class="row">
                <div class="col-md-10 col-md-offset-1">
                    <div class="well page active" id="getting-started" style="display: block;">
                        <div class="col-md-12" style="position:fixed; z-index: 1000;">
                            <div class="col-md-4">
                                <div class="form-group">
                                    <input type="text" class="form-control" placeholder="Search">
                                </div>
                            </div>
                            <div class="col-md-4">
                                <div class="form-group">
                                    <a href="javascript:void(0)" class="btn btn-raised btn-info">@<div class="ripple-container"></div></a>   
                                </div>
                            </div>
                        </div>
                        
                        
                        <div style=" position:relative; float: left; height: 500px; width: 100%; margin-bottom: 40px;">
                            <div id="map"></div>
                        </div>

                        <hr>
                        <p>Os dados de localização podem atrasar, pois o veículo pode estar em locais sem alcance de rede.</p>
                    </div>
                </div>
            </div>
        </div>

        </div>
        <div style="position: fixed;" id="box_fab_button">
            <a style="position: relative; z-index: 10;" id="btn_new" href="javascript:void(0)" class="btn btn-primary btn-fab"><i class="material-icons">add</i></a>
        </div>
        <!-- Material Design for Bootstrap -->
        <script src="dist/js/material.js"></script>
        <script src="dist/js/ripples.min.js"></script>
        <script type="text/javascript">
            $(document).ready(function() 
            {
                $.material.init();
                initMap();
            });
        </script>
</html>