<?php
  require_once("classes/ClienteSoap.class.php");

  function ppr($v)
  {
    echo "<pre>";
    print_r($v);
    echo "</pre>";
  }


  try
  {
    $Cliente = new ClienteSoap();

//    $retorno = $Cliente->localizacao(19, "2017-05-26T09:42:00.000-03:00", "2017-05-26T09:43:00.000-03:00");
//    $retorno = $Cliente->localizacao(19, "2017-05-26T09:42:00.000-03:00", "2017-05-26T09:43:00.000-03:00");
//    $retorno = $Cliente->consultarVeiculo(20);

    ppr($retorno);

    ppr($Cliente->getXml());
  }
  catch (Exception $e)
  {
    ppr("ERRO");
    ppr($e->getMessage());
  }