<?php
  class TelaErro extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    protected function montar()
    {
      $html = new DHtml("Erro");
      $html->addWelcome();

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      echo $html->generate();
    }
  }