<?php
  class MentosSoapController extends Controller
  {
    const TELA_ERRO       = "tela_erro";
    const TELA_MENU       = "tela_menu";
    const TELA_MANUTENCAO = "tela_manutencao";

    private $idTela;

    private $objDados;

    public function __construct()
    {
      $Erro           = new Erro();
      $this->objDados = null;
      parent::__construct($Erro);

      try
      {
        $this->montarTela();
      }
      catch (Exception $e)
      {
        echo "<h1 style='color: darkred; font-size: 24px' align='center'>{$e->getMessage()}</h1>";
      }
    }

    private function montarTela()
    {
      switch ($this->idTela)
      {
        case self::TELA_ERRO:       new TelaErro($this->Erro);                  break;
        case self::TELA_MENU:       new TelaMenu($this->Erro, $this->objDados); break;
        case self::TELA_MANUTENCAO: new TelaManutencao($this->Erro);            break;
      }
    }

    protected function defaultAction()
    {
      $this->idTela   = self::TELA_MENU;
      $Model          = new ModelMenu();
      $this->objDados = $Model->getResultado();
    }

    protected function listarTipoAction()
    {
      $this->idTela   = self::TELA_MENU;
      $Model          = new ModelMenu($this->getParam("form_id_tipo"));
      $this->objDados = $Model->getResultado();
    }

    protected function manutencaoVeiculoAction()
    {
      $this->idTela = self::TELA_MANUTENCAO;
    }

    protected function errorHandler($dsErro)
    {
      if (!isset($this->idTela))
        $this->idTela = self::TELA_ERRO;

      parent::errorHandler($dsErro);
    }
  }