<?php
  function ppr($vl)
  {
    echo "<pre>";
    print_r($vl);
    echo "</pre>";
  }

  function strValue($vl)
  {
    return ($vl !== null && $vl !== "" && $vl !== false);
  }

  function getDsUrl($action, $params = array())
  {
    $dsLink = "menu.php?action=$action";

    foreach ($params AS $key => $val)
      $dsLink .= "&$key=$val";

    return $dsLink;
  }

  function formataPlaca($ds, $to = "pt_BR")
  {
    if ($to == "pt_BR")
      $ds = substr($ds, 0, 3) . "-" . substr($ds, 3);
    elseif ($to == "sys")
      $ds = str_replace("-", "", $ds);

    return $ds;
  }

  function formataDataHora($ds)
  {
    $arAux  = explode("T", $ds);
    $arData = explode("-", $arAux[0]);
    $dsHora = substr($arAux[1], 0, 8);

    return "{$arData[2]}/{$arData[1]}/{$arData[0]} {$dsHora}";
  }

  function obterTipoVeiculo()
  {
    return array(
      1 => "Carro",
      2 => "Caminhão Toco",
      3 => "Caminhão Carreta",
      4 => "Caminhão Bitrem",
      5 => "Caminhão Treminhão",
      6 => "Moto",
      7 => "Triciclo",
      8 => "Quadriciclo"
    );
  }

   function formatarTipo($id)
   {
     $ar = obterTipoVeiculo();
     return $ar[$id];
   }
