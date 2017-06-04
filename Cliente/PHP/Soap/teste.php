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

    $retorno = $Cliente->localizacao(24);

    ppr($retorno);

    ppr($Cliente->getXml());
  }
  catch (Exception $e)
  {
    ppr("ERRO");
    ppr($e->getMessage());
  }