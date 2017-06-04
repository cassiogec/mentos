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

    /**
     * Tela constructor.
     * @param Erro $Erro
     * @param null|array|stdClass $objDados
     */
    public function __construct(Erro $Erro, $objDados = null)
    {
      if (isset($objDados))
        $this->objDados = $objDados;

      $this->Erro = $Erro;
      $this->montar();
    }

    /**
     * @throws Exception
     */
    protected function montar()
    {
      throw new Exception("Erro desconhecido");
    }
  }