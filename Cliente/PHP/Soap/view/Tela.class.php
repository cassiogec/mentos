<?php
  class Tela
  {
    /**
     * @var null|stdClass|array
     */
    protected $objDados;

    /**
     * @var Erro
     */
    protected $Erro;

    public function __construct(Erro $Erro, $objDados = null)
    {
      if (isset($objDados))
        $this->objDados = $objDados;

      $this->Erro = $Erro;
      $this->montar();
    }

    protected function montar()
    {
      throw new Exception("Erro desconhecido");
    }
  }