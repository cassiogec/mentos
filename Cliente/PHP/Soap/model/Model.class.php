<?php
  class Model
  {
    /**
     * @var stdClass|array|mixed
     */
    private $objRetorno;

    /**
     * Model constructor.
     */
    public function __construct()
    {
      $this->objRetorno = new stdClass();
    }

    /**
     * @param $dsPlaca
     * @param $idTipo
     * @param $vlCapacidade
     * @param $dsUnidade
     * @return array|bool|mixed|stdClass
     * @throws Exception
     */
    public function adicionarVeiculo($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade)
    {
      $this->validarValores($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade);

      $Cliente          = new ClienteSoap();
      $this->objRetorno = $Cliente->adicionarVeiculo($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade);
      return $this->trataDadosRetorno($this->objRetorno);
    }

    /**
     * @param $cdVeiculo
     * @param $dsPlaca
     * @param $idTipo
     * @param $vlCapacidade
     * @param $dsUnidade
     * @return array|bool|mixed|stdClass
     * @throws Exception
     */
    public function alterarVeiculo($cdVeiculo, $dsPlaca, $idTipo, $vlCapacidade, $dsUnidade)
    {
      $this->validarValores($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade, $cdVeiculo);

      $Cliente          = new ClienteSoap();
      $this->objRetorno = $Cliente->alterarVeiculo($cdVeiculo, $dsPlaca, $idTipo, $vlCapacidade, $dsUnidade);
      return $this->trataDadosRetorno($this->objRetorno);
    }

    /**
     * @param $cdVeiculo
     * @return array|bool|mixed|stdClass
     * @throws Exception
     */
    public function excluirVeiculo($cdVeiculo)
    {
      $this->validarValores(true, true, true, true, $cdVeiculo);

      $Cliente          = new ClienteSoap();
      $this->objRetorno = $Cliente->excluirVeiculo($cdVeiculo);
      return $this->trataDadosRetorno($this->objRetorno);
    }

    /**
     * @param string $idTipo
     * @return array
     */
    public function listarVeiculosMenu($idTipo = "")
    {
      $Cliente = new ClienteSoap();

      if (strValue($idTipo))
        $this->objRetorno = $Cliente->listaTipo($idTipo);
      else
        $this->objRetorno = $Cliente->listaTodosVeiculos();

      return $this->retornaArray($this->objRetorno);
    }

    /**
     * @param $cdVeiculo
     * @return array|bool|mixed|stdClass
     */
    public function consultarVeiculoManutencao($cdVeiculo)
    {
      $Cliente          = new ClienteSoap();
      $this->objRetorno = $Cliente->consultarVeiculo($cdVeiculo);
      return $this->trataDadosRetorno($this->objRetorno);
    }

    /**
     * @param $cdVeiculo
     * @param $dtInicio
     * @param $tmInicio
     * @return array
     */
    public function consultarLocalizacao($cdVeiculo, $dtInicio, $tmInicio)
    {
      $dtLocalizacaoInicio = "{$dtInicio}T{$tmInicio}:00.000-03:00";

      $Cliente          = new ClienteSoap();
      $this->objRetorno = $Cliente->localizacao($cdVeiculo, $dtLocalizacaoInicio);
      return $this->retornaArray($this->objRetorno);
    }

    /**
     * @param $dsPlaca
     * @param $idTipo
     * @param $vlCapacidade
     * @param $dsUnidade
     * @param bool $cdVeiculo
     * @throws Exception
     */
    private function validarValores($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade, $cdVeiculo = true)
    {
      if (!strValue($cdVeiculo))
        throw new Exception("Código de Veículo não encontrado!");

      if (!strValue($dsPlaca))
        throw new Exception("Preencha o campo Placa!");

      if (!strValue($idTipo))
        throw new Exception("Preencha o campo Tipo!");

      if (!strValue($vlCapacidade))
        throw new Exception("Preencha o campo Capacidade!");

      if (!strValue($dsUnidade))
        throw new Exception("Preencha o campo Unidade!");
    }

    /**
     * @param $objRetorno
     * @return array
     */
    private function retornaArray($objRetorno)
    {
      if (is_array($objRetorno))
        $arrRetorno = $objRetorno;
      elseif (is_object($objRetorno))
        $arrRetorno = array($objRetorno);
      else
        $arrRetorno = array();

      return $this->trataDadosRetorno($arrRetorno);
    }

    /**
     * @param $objRetorno
     * @return mixed
     */
    private function trataDadosRetorno($objRetorno)
    {
      if (is_array($objRetorno))
      {
        foreach ($objRetorno AS &$val)
          $val = $this->tratarValor($val);
      }
      else
        $objRetorno = $this->tratarValor($objRetorno);

      return $objRetorno;
    }

    /**
     * @param $val
     * @return mixed
     */
    private function tratarValor($val)
    {
      if (isset($val->placa))
        $val->placa = formataPlaca($val->placa);

      if (isset($val->datahora))
        $val->datahora = formataDataHora($val->datahora);
      
      if (isset($val->tipo))
      {
        $val->dsTipo = formatarTipo($val->tipo);

        if (!strValue($val->dsTipo))
          $val->dsTipo = $val->tipo;
      }

      return $val;
    }

  }