<?php
  class TelaIP extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    public function montar()
    {
      $html = new DHtml("Alteração de IP do Servidor");

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $form = new DForm("confirmarIP", true, "Alteração de IP do Servidor");

      $form->add(DForm::getInput("text", "IP", "ds_IP", "Digite o IP", $_SESSION["s_ds_ip"]));
      $form->add(DForm::getSubmitButton("Alterar"));
      $html->add($form);

      echo $html->generate();
    }
  }
