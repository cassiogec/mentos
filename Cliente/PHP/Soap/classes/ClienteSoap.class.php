<?php
  class ClienteSoap
  {
    /**
     * @var SoapClient
     */
    private $Client;

    /**
     * @var mixed
     */
    private $lastResponse;

    /**
     * @var string
     */
    private $dsXml;

    /**
     * @var array
     */
    private $error = array();

    /**
     * @var string
     */
    private static $wsdl = "http://127.0.0.1:9876/Servidor/WebServiceSoap?wsdl";

    /**
     * @var bool
     */
    public $idSilence = false;

    /**
     * ClienteSoap constructor.
     * @param bool $idSilence
     */
    public function __construct($idSilence = false)
    {
      $this->idSilence = $idSilence;
      $this->createNewSoapClient();
    }

    /**
     * Faz o Unset do SoapClient e cria um novo
     */
    private function createNewSoapClient()
    {
      try
      {
        unset($this->Client);
        $this->Client = new SoapClient(self::$wsdl, array('trace' => 1));
      }
      catch (Exception $e)
      {
        $this->tratarErro($e->getMessage());
      }
    }

    /**
     * Tratamento de erro
     * @param $errorMsg
     * @throws Exception
     */
    private function tratarErro($errorMsg)
    {
      if (strpos($errorMsg, "failed to load external entity") !== false)
        $errorMsg = "Erro na comunicação com o Servidor";

      $this->error[] = $errorMsg;

      if (!$this->idSilence && count($this->error))
        throw new Exception(implode("<br>", $this->error));
    }

    /**
     * @param $param
     * @param $val
     * @return array
     */
    private function param($param, $val)
    {
      switch ($param)
      {
        case "cdVeiculo":     $val = (int)    $val; break;
        case "dsPlaca":       $val = (string) $val; break;
        case "idTipo":        $val = (int)    $val; break;
        case "vlCapacidade":  $val = (float)  $val; break;
        case "dsUnidade":     $val = (string) $val; break;
        case "dtLocalizacao": $val = (string) $val; break;
        default:              $val = (string) $val;
      }

      return array($param => $val);
    }

    /**
     * @return array
     * @throws Exception
     */
    private function paramsHandler()
    {
      $args    = func_get_args();
      $retorno = array();

      foreach ($args AS &$param)
      {
        if (!is_array($param))
          throw new Exception("Parâmetros inválidos");

        $retorno = array_merge($retorno, $param);
      }

      return $retorno;
    }

    /**
     * @param $dsPlaca
     * @param $idTipo
     * @param $vlCapacidade
     * @param $dsUnidade
     * @return bool|mixed
     */
    public function adicionarVeiculo($dsPlaca, $idTipo, $vlCapacidade, $dsUnidade)
    {
      return $this->execute("adicionarVeiculo", $this->paramsHandler(
        $this->param("dsPlaca",      $dsPlaca),
        $this->param("idTipo",       $idTipo),
        $this->param("vlCapacidade", $vlCapacidade),
        $this->param("dsUnidade",    $dsUnidade)
      ));
    }

    /**
     * @param $cdVeiculo
     * @param $dsPlaca
     * @param $idTipo
     * @param $vlCapacidade
     * @param $dsUnidade
     * @return bool|mixed
     */
    public function alterarVeiculo($cdVeiculo, $dsPlaca, $idTipo, $vlCapacidade, $dsUnidade)
    {
      return $this->execute("alterarVeiculo", $this->paramsHandler(
        $this->param("cdVeiculo",    $cdVeiculo),
        $this->param("dsPlaca",      $dsPlaca),
        $this->param("idTipo",       $idTipo),
        $this->param("vlCapacidade", $vlCapacidade),
        $this->param("dsUnidade",    $dsUnidade)
      ));
    }

    /**
     * @param $cdVeiculo
     * @return bool|mixed
     */
    public function excluirVeiculo($cdVeiculo)
    {
      return $this->execute("excluirVeiculo", $this->paramsHandler(
        $this->param("cdVeiculo", $cdVeiculo)
      ));
    }

    /**
     * @param $cdVeiculo
     * @return bool|mixed
     */
    public function consultarVeiculo($cdVeiculo)
    {
      return $this->execute("consultarVeiculo", $this->paramsHandler(
        $this->param("cdVeiculo", $cdVeiculo)
      ));
    }

    /**
     * @param $idTipo
     * @return bool|mixed
     */
    public function listaTipo($idTipo)
    {
      return $this->execute("listaTipo", $this->paramsHandler(
        $this->param("idTipo", $idTipo)
      ));
    }

    /**
     * @return bool|mixed
     */
    public function listaTodosVeiculos()
    {
      return $this->execute("listaTodosVeiculos");
    }

    /**
     * @param $cdVeiculo
     * @param $dtLocalizacao
     * @return bool|mixed
     */
    public function localizacao($cdVeiculo, $dtLocalizacao = null)
    {
      return $this->execute("localizacao", $this->paramsHandler(
        $this->param("cdVeiculo",     $cdVeiculo),
        $this->param("dtLocalizacao", $dtLocalizacao)
      ));
    }

    /**
     * Executa os métodos
     * @param $method
     * @param $params
     * @return bool|mixed
     * @throws Exception
     */
    private function execute($method, $params = array())
    {
      try
      {
        $params = array_filter($params);

        if (count($params))
          $this->lastResponse = $this->Client->$method(array_filter($params));
        else
          $this->lastResponse = $this->Client->$method();

        if (!isset($this->lastResponse->{"return"}))
          $this->lastResponse->return = "Sem Dados";

        $this->dsXml = $this->Client->__getLastRequest();
        $this->createNewSoapClient();
        return $this->lastResponse->{"return"};
      }
      catch (SoapFault $fault)
      {
        if ($fault->getCode() == "HTTP" && $fault->getMessage() == "Error Fetching http headers")
          $this->tratarErro("Erro na comunicação com o Servidor, por favor tente mais tarde!");
        else
          $this->tratarErro($fault->getMessage());

        return false;
      }
      catch (Exception $e)
      {
        $this->tratarErro($e->getMessage());
        return false;
      }
    }

    /**
     * @return mixed
     */
    public function getLastResponse()
    {
      return $this->lastResponse;
    }

    /**
     * @return string
     */
    public function getXml()
    {
      return htmlentities($this->dsXml);
    }
  }