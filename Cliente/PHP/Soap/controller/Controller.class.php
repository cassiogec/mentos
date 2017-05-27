<?php
  class Controller
  {
    /**
     * @var Erro
     */
    protected $Erro;

    /**
     * Controller constructor.
     * @param Erro $Erro
     * @param string|null $action
     */
    public function __construct(Erro $Erro, $action = null)
    {
      $this->Erro = $Erro;

      try
      {
        $this->callAction($action);
      }
      catch (Exception $e)
      {
        $this->errorHandler($e->getMessage());
      }
    }

    /**
     * @param $dsErro
     */
    protected function errorHandler($dsErro)
    {
      $this->Erro->setError($dsErro);
    }

    /**
     * @param $action
     * @throws Exception
     */
    protected function callAction($action)
    {
      if (isset($action) && strValue($action))
        $method = $action;
      elseif (!isset($_REQUEST["action"]))
        $method = "defaultAction";
      else
        $method = ((string) $_REQUEST["action"]) . "Action";

      if (!method_exists($this, $method))
        throw new Exception("Método inválido!");
      else
        $this->$method();
    }

    /**
     * @param $nmParam
     * @return null
     */
    protected function getParam($nmParam)
    {
      if (isset($_REQUEST[$nmParam]))
        return $_REQUEST[$nmParam];
      else
        return null;
    }
  }