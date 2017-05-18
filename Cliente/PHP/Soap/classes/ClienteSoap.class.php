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
    private $response;

    /**
     * @var string
     */
    private static $wsdl = "http://127.0.0.1:9876/Servidor/WebServiceSoap?wsdl";

    /**
     * ClienteSoap constructor.
     */
    public function __construct()
    {
      $this->Client = new SoapClient(self::$wsdl, array('trace' => 1));
    }

    /**
     * Responsável por ajustar os parâmetros e mandar a requisição
     * @param $name
     * @param $arguments
     * @return mixed
     */
    public function __call($name, $arguments)
    {
      $nrArg  = 0;
      $params = array();

      foreach ($arguments AS $val)
      {
        $params["arg{$nrArg}"] = $val;
        $nrArg++;
      }

      $this->response = $this->Client->$name($params);
      return $this->response;
    }

    /**
     * @return mixed
     */
    public function getResponse()
    {
      return $this->response;
    }

    /**
     * @return string
     */
    public function getXml()
    {
      return htmlentities($this->Client->__getLastRequest());
    }
  }