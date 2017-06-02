<?php
  class MentosSoapController extends Controller
  {
    const TELA_ERRO        = "tela_erro";
    const TELA_MENU        = "tela_menu";
    const TELA_MANUTENCAO  = "tela_manutencao";
    const TELA_LOCALIZACAO = "tela_localizacao";
    const TELA_CONSULTA    = "tela_consulta";
    const TELA_IP          = "tela_ip";

    /**
     * @var string
     */
    private $idTela;

    /**
     * @var null|array|stdClass
     */
    private $objDados;

    /**
     * @var Model
     */
    private $Model;

    /**
     * MentosSoapController constructor.
     */
    public function __construct()
    {
      $Erro           = new Erro();
      $this->objDados = null;
      $this->Model    = new Model();
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

    /**
     * Controle de Telas
     */
    private function montarTela()
    {
      switch ($this->idTela)
      {
        case self::TELA_ERRO:        new TelaErro(       $this->Erro                 ); break;
        case self::TELA_MENU:        new TelaMenu(       $this->Erro, $this->objDados); break;
        case self::TELA_MANUTENCAO:  new TelaManutencao( $this->Erro, $this->objDados); break;
        case self::TELA_LOCALIZACAO: new TelaLocalizacao($this->Erro, $this->objDados); break;
        case self::TELA_CONSULTA:    new TelaConsulta(   $this->Erro, $this->objDados); break;
        case self::TELA_IP:          new TelaIP(         $this->Erro                 ); break;
      }
    }

    /**
     * Ação Default
     */
    protected function defaultAction()
    {
      $this->idTela   = self::TELA_MENU;
      $this->objDados = $this->Model->listarVeiculosMenu();
    }

    /**
     * Lista os Veículos por Tipo
     */
    protected function listarTipoAction()
    {
      $this->idTela   = self::TELA_MENU;

      $idTipoFiltro = $this->getParam("form_id_tipo_filtro");
      $cdValor      = "";

      switch ($idTipoFiltro)
      {
        case "codigo":
          $cdValor = $this->getParam("form_cd_veiculo");
        break;
        case "placa":
          $cdValor = $this->getParam("form_ds_placa");
        break;
        case "tipo":
          $cdValor = $this->getParam("form_id_tipo");
        break;
      }

      $this->objDados = $this->Model->listarVeiculosMenu($idTipoFiltro, $cdValor);
    }

    /**
     * Carrega os dados para Manutenção
     */
    protected function manutencaoVeiculoAction()
    {
      $this->idTela = self::TELA_MANUTENCAO;
      $cdVeiculo    = $this->getParam("form_cd_veiculo");

      if (strValue($cdVeiculo))
        $this->objDados = $this->Model->consultarVeiculoManutencao($cdVeiculo);
    }

    /**
     * Adiciona um Veículo
     */
    protected function adicionarVeiculoAction()
    {
      $this->idTela   = self::TELA_MANUTENCAO;
      $this->objDados = $this->Model->adicionarVeiculo(
        $this->getParam("form_ds_placa"),
        $this->getParam("form_id_tipo"),
        $this->getParam("form_vl_capacidade"),
        $this->getParam("form_ds_unidade")
      );

      if ($this->objDados)
        $this->infoMessage("Veículo adicionado com Sucesso");
    }

    /**
     * Altera um Veículo
     */
    protected function alterarVeiculoAction()
    {
      $this->idTela   = self::TELA_MANUTENCAO;
      $this->objDados = $this->Model->alterarVeiculo(
        $this->getParam("form_cd_veiculo"),
        $this->getParam("form_ds_placa"),
        $this->getParam("form_id_tipo"),
        $this->getParam("form_vl_capacidade"),
        $this->getParam("form_ds_unidade")
      );

      if ($this->objDados)
        $this->infoMessage("Veículo alterado com Sucesso");
    }

    /**
     * Exclui um Veículo
     */
    protected function excluirVeiculoAction()
    {
      $this->objDados = $this->Model->excluirVeiculo(
        $this->getParam("form_cd_veiculo")
      );

      if ($this->objDados)
        $this->infoMessage("Veículo excluído com Sucesso");
    }

    /**
     * Retorna a localização de um Veículo e seus dados confome filtro
     */
    protected function localizacaoVeiculoAction()
    {
      $this->objDados               = new stdClass();
      $this->objDados->dadosVeiculo = $this->Model->consultarVeiculoManutencao(
        $this->getParam("form_cd_veiculo")
      );

      $this->idTela = self::TELA_LOCALIZACAO;

      if ($this->getParam("form_id_submitted"))
      {
        $this->objDados->arrLocalizacao = $this->Model->consultarLocalizacao(
          $this->getParam("form_cd_veiculo"),
          $this->getParam("form_dt_inicio"),
          $this->getParam("form_tm_inicio")
        );
      }
    }

    /**
     * Faz a consulta dos dados de um Veículo
     */
    protected function consultarVeiculoAction()
    {
      $this->objDados = $this->Model->consultarVeiculoManutencao($this->getParam("form_cd_veiculo"));
      $this->idTela   = self::TELA_CONSULTA;
    }

    /**
     * Tela para alterar IP do servidor
     */
    protected function alterarIPAction()
    {
      $this->idTela = self::TELA_IP;
    }

    /**
     * Alteração do IP
     */
    protected function confirmarIPAction()
    {
      if (strValue($this->getParam("form_ds_IP")))
        $_SESSION["s_ds_ip"] = $this->getParam("form_ds_IP");

      $this->infoMessage("IP do Servidor alterado com sucesso");
    }

    /**
     * Tratamento de Erro
     * @param $dsErro
     */
    protected function errorHandler($dsErro)
    {
      if (!isset($this->idTela))
        $this->idTela = self::TELA_ERRO;

      parent::errorHandler($dsErro);
    }

    /**
     * Redirecionamento e mensagem de info para quando houver alguma alteração de banco
     * @param $dsMsg
     */
    private function infoMessage($dsMsg)
    {
      echo "<script>window.location.href=\"menu.php?info_message={$dsMsg}\"</script>";
    }
  }