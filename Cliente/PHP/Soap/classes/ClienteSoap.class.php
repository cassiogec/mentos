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
     */
    public function __construct()
    {
      $this->createNewSoapClient();
    }

    private function createNewSoapClient()
    {
      unset($this->Client);
      $this->Client = new SoapClient(self::$wsdl, array('trace' => 1));
    }

    /**
     * Tratamento de erro
     * @param $errorMsg
     * @throws Exception
     */
    private function tratarErro($errorMsg)
    {
      $this->error[] = $errorMsg;

      if (!$this->idSilence && count($this->error))
        throw new Exception(implode("<br>", $this->error));
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
      $params = array(
        "dsPlaca"      => $dsPlaca,
        "idTipo"       => $idTipo,
        "vlCapacidade" => $vlCapacidade,
        "dsUnidade"    => $dsUnidade
      );

      return $this->execute("adicionarVeiculo", $params);
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
      $params = array(
        "cdVeiculo"    => $cdVeiculo,
        "dsPlaca"      => $dsPlaca,
        "idTipo"       => $idTipo,
        "vlCapacidade" => $vlCapacidade,
        "dsUnidade"    => $dsUnidade
      );

      return $this->execute("alterarVeiculo", $params);
    }

    /**
     * @param $cdVeiculo
     * @return bool|mixed
     */
    public function excluirVeiculo($cdVeiculo)
    {
      $params = array(
        "cdVeiculo" => $cdVeiculo
      );

      return $this->execute("excluirVeiculo", $params);
    }

    /**
     * @param $cdVeiculo
     * @return bool|mixed
     */
    public function consultarVeiculo($cdVeiculo)
    {
      $params = array(
        "cdVeiculo" => (int) $cdVeiculo
      );

      return $this->execute("consultarVeiculo", $params);
    }

    /**
     * @param $idTipo
     * @return bool|mixed
     */
    public function listaTipo($idTipo)
    {
      $params = array(
        "idTipo" => $idTipo
      );

      return $this->execute("listaTipo", $params);
    }

    /**
     * @param $cdVeiculo
     * @param $dtLocalizacao
     * @return bool|mixed
     */
    public function localizacao($cdVeiculo, $dtLocalizacao = null)
    {
      $params = array(
        "cdVeiculo"     => $cdVeiculo,
        "dtLocalizacao" => $dtLocalizacao
      );

      return $this->execute("localizacao", $params);
    }

    /**
     * Executa os métodos
     * @param $method
     * @param $params
     * @return bool|mixed
     * @throws Exception
     */
    private function execute($method, $params)
    {
      try
      {
        $this->lastResponse = $this->Client->$method(array_filter($params));

        if (!isset($this->lastResponse->{"return"}))
          $this->lastResponse->return = "Sem Dados";

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
      return htmlentities($this->Client->__getLastRequest());
    }
  }