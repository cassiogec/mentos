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

//    $retorno = $Cliente->adicionarVeiculo(
//      "T102BR1", 1, 1000, "TONEL"
//    );

//    $retorno = $Cliente->alterarVeiculo(
//      17, "TIO2BRI", 1, 1000, "KILOG"
//    );

//    $retorno = $Cliente->excluirVeiculo(17);

//    $retorno = $Cliente->consultarVeiculo(13);

//    $retorno = $Cliente->localizacao(5);
//
//    ppr($retorno);

//    $retorno = $Cliente->listaTipo(1);
//
//    ppr($retorno);

    ppr($Cliente->getXml());
  }
  catch (Exception $e)
  {
    ppr("ERRO");
    ppr($e->getMessage());
  }
