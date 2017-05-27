<?php
  class TelaManutencao extends Tela
  {
    public function montar()
    {
      $html = new DHtml("Cadastro Veículo");

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $form = new DForm("teste", true, "Cadastro de Veículo");
      $form->add(DForm::getInput("text",   "Placa", "ds_placa", "Digite a Placa"));
      $form->add(DForm::getInput("number", "Tipo",  "id_tipo",  "Digite o Tipo"));


      $html->add($form);

      echo $html->generate();
    }
  }
