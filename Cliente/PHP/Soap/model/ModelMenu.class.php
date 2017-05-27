<?php
  class ModelMenu
  {
    private $objRetorno;

    public function __construct($idTipo = "")
    {
      $Cliente = new ClienteSoap();

      if (strValue($idTipo))
        $this->objRetorno = $Cliente->listaTipo($idTipo);
      else
        $this->objRetorno = $Cliente->listaTodosVeiculos();
    }

    public function getResultado()
    {
      return $this->objRetorno;
    }
  }