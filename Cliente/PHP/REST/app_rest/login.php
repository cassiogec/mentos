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
  <link href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.6/css/bootstrap.min.css" rel="stylesheet">

  <!-- Bootstrap Material Design -->
  <link href="dist/css/bootstrap-material-design.css" rel="stylesheet">
  <link href="dist/css/ripples.min.css" rel="stylesheet">

  <!-- Dropdown.js -->
  <link href="//cdn.rawgit.com/FezVrasta/dropdown.js/master/jquery.dropdown.css" rel="stylesheet">

  <!-- Page style -->
  <link href="index.css" rel="stylesheet">

  <!-- jQuery -->
  <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
      
  <!-- Latest compiled and minified JavaScript -->
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
    </head>
    <body>
        <div class="bs-component">
          <div style="font-weight: bold; background-color: #6495ED; box-shadow: 0 0 4px rgba(0,0,0,.14),0 4px 8px rgba(0,0,0,.28);" class="navbar">
            <div class="container">
              <div class="container-fluid">
              <div class="navbar-header">
                <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-warning-collapse">
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                  <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="javascript:void(0)">Brand</a>
              </div>
              <div class="navbar-collapse navbar-warning-collapse collapse in" aria-expanded="true">
                
                <ul class="nav navbar-nav navbar-right">
                  <li><a href="">Cadastrar-se</a></li>
                    
<!--                  <li class="dropdown">
                    <a href="" data-target="#" class="dropdown-toggle" data-toggle="dropdown" aria-expanded="false">Dropdown
                      <b class="caret"></b></a>
                    <ul class="dropdown-menu">
                      <li><a href="">Action</a></li>
                      <li><a href="">Another action</a></li>
                      <li><a href="javascript:void(0)">Something else here</a></li>
                      <li class="divider"></li>
                      <li><a href="javascript:void(0)">Sair</a></li>
                    </ul>
                  </li>-->
                </ul>
              </div>
                  </div>
          </div>
        </div>
        <div class="container-fluid main">
            <div class="col-md-6 col-md-offset-1">
                <div class="well bs-component">
                <form class="form-horizontal">
  <fieldset>
    <legend>Login</legend>
    <div class="form-group has-success">
      <label for="inputEmail" class="col-md-2 control-label">Email</label>

      <div class="col-md-10">
        <input type="email" class="form-control" id="inputEmail" placeholder="Email">
      </div>
    </div>
    <div class="form-group has-success">
      <label for="inputPassword" class="col-md-2 control-label">Password</label>

      <div class="col-md-10">
        <input type="password" class="form-control" id="inputPassword" placeholder="Password">
      </div>
    </div>
    <div class="form-group">
        <div class="col-md-3 col-md-offset-2">
            <a href="javascript:void(0)" class="btn btn-raised btn-success">Logar<div class="ripple-container"></div></a>
        </div>
        <div class="col-md-4">
            <div class="checkbox">
                <label>
                    <input type="checkbox" name="remember"> Remember Me
                </label>
            </div>
        </div>
    </div>
    </div>
  </fieldset>
</form>


        </div>
            </div>
        </div>
        
        <!-- Open source code -->
<!--<script>
  window.page = window.location.hash || "#about";

  $(document).ready(function () {
    if (window.page != "#about") {
      $(".menu").find("li[data-target=" + window.page + "]").trigger("click");
    }
  });

//  $(window).on("resize", function () {
//    $("html, body").height($(window).height());
//    $(".main, .menu").height($(window).height() - $(".header-panel").outerHeight());
//    $(".pages").height($(window).height());
//  }).trigger("resize");

  $(".menu li").click(function () {
    // Menu
    if (!$(this).data("target")) return;
    if ($(this).is(".active")) return;
    $(".menu li").not($(this)).removeClass("active");
    $(".page").not(page).removeClass("active").hide();
    window.page = $(this).data("target");
    var page = $(window.page);
    window.location.hash = window.page;
    $(this).addClass("active");


    page.show();

    var totop = setInterval(function () {
      $(".pages").animate({scrollTop: 0}, 0);
    }, 1);

    setTimeout(function () {
      page.addClass("active");
      setTimeout(function () {
        clearInterval(totop);
      }, 1000);
    }, 100);
  });

  function cleanSource(html) {
    var lines = html.split(/\n/);

    lines.shift();
    lines.splice(-1, 1);

    var indentSize = lines[0].length - lines[0].trim().length,
        re = new RegExp(" {" + indentSize + "}");

    lines = lines.map(function (line) {
      if (line.match(re)) {
        line = line.substring(indentSize);
      }

      return line;
    });

    lines = lines.join("\n");

    return lines;
  }

  $("#opensource").click(function () {
    $.get(window.location.href, function (data) {
      var html = $(data).find(window.page).html();
      html = cleanSource(html);
      $("#source-modal pre").text(html);
      $("#source-modal").modal();
    });
  });
</script>-->

        <!-- Material Design for Bootstrap -->
<script src="dist/js/material.js"></script>
<script src="dist/js/ripples.min.js"></script>
<script type="text/javascript">
    $(document).ready(function() {
        $.material.init();
    });
</script>
</html>