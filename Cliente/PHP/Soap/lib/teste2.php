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

  <style>
    html, body {
      height: 100%;
      margin: 0;
      padding: 0;
    }
  </style>

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
          <a class="navbar-brand" href="index.php">Mentos</a>
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
        <div class="well page active" id="getting-started">

          <div class=" col-md-12">
            <!--                            <div class="col-md-4">
                                            <div class="form-group">
                                                <input type="text" class="form-control" placeholder="Search">
                                            </div>
                                        </div>
                                        <div class="col-md-4">
                                            <div class="form-group">
                                                <input type="text" class="form-control" placeholder="Search">
                                            </div>
                                        </div>-->
            <h1>Olá administrador!</h1>

          </div>
          <h3>Hmm, nada por aqui!</h3>
        </div>

      </div>
      <div class="col-md-10 col-md-offset-1">
        <div class="panel panel-default">
          <div class="panel-heading">Paniel de Listagem</div>
          <table class="table">
            <thead>
            <tr>
              <th>#</th>
              <th>Placa</th>
              <th>Tipo</th>
              <th>Capacidade</th>
              <th>Outra informação</th>
              <th></th>
              <th></th>
            </tr>
            </thead>
            <tbody>
            <tr>
              <th scope="row">1</th>
              <td>Mark</td>
              <td>Otto</td>
              <td>@mdo</td>
              <td>@mdo</td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-success">Alterar</a>
              </td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-danger">Excluir</a>
              </td>
            </tr>
            <tr>
              <th scope="row">2</th>
              <td>Jacob</td>
              <td>Thornton</td>
              <td>@fat</td>
              <td>@mdo</td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-success">Alterar</a>
              </td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-danger">Excluir</a>
              </td>

            </tr>
            <tr>
              <th scope="row">3</th>
              <td>Larry</td>
              <td>the Bird</td>
              <td>@twitter</td>
              <td>@mdo</td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-success">Alterar</a>
              </td>
              <td>
                <a style="margin:0px; " href="javascript:void(0)" class="btn btn-block btn-danger">Excluir</a>
              </td>
            </tr>
            </tbody>
          </table>
        </div>
      </div>
    </div>
  </div>

</div>
<!-- Material Design for Bootstrap -->
<script src="dist/js/material.js"></script>
<script src="dist/js/ripples.min.js"></script>
</html>