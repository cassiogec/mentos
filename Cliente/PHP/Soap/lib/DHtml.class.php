<?php
  class DHtml extends DBasicHtml
  {
    public function __construct($dsTitle = "")
    {
      parent::__construct();

      $this->dsHtml = <<<HTML
        <!DOCTYPE html>
        <html>
        <head>
          <meta charset="UTF-8">
          <title>Mentos - {$dsTitle}</title>
        
          <meta http-equiv="X-UA-Compatible" content="IE=edge">
        
          <!-- Mobile support -->
          <meta name="viewport" content="width=device-width, initial-scale=1">
        
          <!-- Material Design fonts -->
          <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:300,400,500,700" type="text/css">
          <link href="https://fonts.googleapis.com/icon?family=Material+Icons" rel="stylesheet">
        
          <!-- Bootstrap -->
          <link href="lib/css/boostrap.min.css" rel="stylesheet">
        
          <!-- Bootstrap Material Design -->
          <link href="lib/dist/css/bootstrap-material-design.css" rel="stylesheet">
          <link href="lib/dist/css/ripples.min.css" rel="stylesheet">
          <!-- Dropdown.js -->
          <link href="lib/css/jquery.dropdown.css" rel="stylesheet">
          <!-- Page style -->
          <link href="lib/index.css" rel="stylesheet">
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
          <script src="lib/js/boostrap.min.js" ></script>
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
                  <a class="navbar-brand" href="menu.php">Mentos</a>
                </div>
                <div class="navbar-collapse navbar-warning-collapse collapse in" aria-expanded="true">
                  <ul class="nav navbar-nav">
                    <li class="dropdown">
                      <a href="" data-target="#" class="dropdown-toggle" data-toggle="dropdown">Menu
                        <b class="caret"></b></a>
                      <ul class="dropdown-menu">
                        <li><a href="menu.php?action=manutencaoVeiculo">Adicionar Veículo</a></li>
                      </ul>
                    </li>
                  </ul>
                </div>
              </div>
            </div>
          </div>
          <div class="container-fluid main">
          <div class="row">
HTML;
    }

    public function addAlertMessage($dsMsg, $style = "danger", $idSuportMessage = true)
    {
      if (strValue($dsMsg))
      {
        $dsMsgSuporte = "";

        if ($idSuportMessage)
          $dsMsgSuporte = "<div align=\"center\"><h4><b>Por Favor entre em contato com o Suporte!</b></h4></div>";

        $this->dsHtml .= <<<HTML
          <div class="col-md-10 col-md-offset-1">
            <div class="active alert-{$style} text-{$style}">
              <div class=" col-md-12"><h3><b>Status:</b></h3></div>
              <div class=" col-md-12"><h4><b>{$dsMsg}</b></h4></div>
              {$dsMsgSuporte}
              <br>
            </div>
          </div>
HTML;
      }
    }

    public function addTitle($dsTitle)
    {
      $this->dsHtml .= <<<HTML
        <div class="col-md-10 col-md-offset-1">
          <div class="well page active" id="getting-started">
            <h1>{$dsTitle}</h1>
          </div>
        </div>
HTML;
    }

    public function addWelcome()
    {
      $this->dsHtml .= <<<HTML
        <div class="col-md-10 col-md-offset-1">
          <div class="well page active" id="getting-started">
            <div class=" col-md-12"><h1>Bem Vindo à Mentos</h1></div>
            <h3>Sistema de localização de Veículos</h3>
          </div>
        </div>
HTML;
    }

    public function generate()
    {
      parent::generate();
      $this->dsHtml .= <<<HTML
          </div>
          </div>
        </div>
        <!-- Material Design for Bootstrap -->
        <script src="lib/dist/js/material.js"></script>
        <script src="lib/dist/js/ripples.min.js"></script>
        </html>
HTML;
      return $this->dsHtml;
    }
  }