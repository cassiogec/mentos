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
    $retorno = $Cliente->adicionarVeiculo("AAA-1111", 0, 1000, "Tonelada");

    ppr($retorno);

    ppr($Cliente->getXml());
  }
  catch (SoapFault $fault)
  {
    ppr("ERRO");
    ppr($fault->getMessage());
  }