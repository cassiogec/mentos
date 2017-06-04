<?php
  ini_set('default_socket_timeout', 120);
  ini_set('max_execution_time',     120);
  set_time_limit(120);
  error_reporting(~E_NOTICE);

  require_once("include/functions.php");
  require_once("lib/DBasicHtml.class.php");
  require_once("lib/DHtml.class.php");
  require_once("lib/DTable.class.php");
  require_once("lib/DForm.class.php");

  require_once("classes/ClienteSoap.class.php");
  require_once("classes/Erro.class.php");

  require_once("controller/Controller.class.php");
  require_once("controller/MentosSoapController.class.php");

  require_once("view/Tela.class.php");
  require_once("view/TelaErro.class.php");
  require_once("view/TelaMenu.class.php");
  require_once("view/TelaManutencao.class.php");
  require_once("view/TelaConsulta.class.php");
  require_once("view/TelaLocalizacao.class.php");
  require_once("view/TelaIP.class.php");

  require_once("model/Model.class.php");