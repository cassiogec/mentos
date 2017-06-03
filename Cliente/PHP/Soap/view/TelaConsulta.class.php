<?php
  class TelaConsulta extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    public function montar()
    {
      $html = new DHtml("Consulta de Veículo");

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $form = new DForm("", true, "Consulta de Veículo");

      $form->add(<<<HTML
        <div class="form-group has-success">
          <label class="col-md-2 control-label" style="font-size: 17px"><b>Código</b></label>
          <div class="col-md-10">
            <label class="control-label" style="font-size: 17px"><b>{$this->objDados->codigo}</b></label>
          </div>
        </div>
        <div class="form-group has-success">
          <label class="col-md-2 control-label" style="font-size: 17px"><b>Placa</b></label>
          <div class="col-md-10">
            <label class="control-label" style="font-size: 17px"><b>{$this->objDados->placa}</b></label>
          </div>
        </div>
        <div class="form-group has-success">
          <label class="col-md-2 control-label" style="font-size: 17px"><b>Tipo</b></label>
          <div class="col-md-10">
            <label class="control-label" style="font-size: 17px"><b>{$this->objDados->dsTipo}</b></label>
          </div>
        </div>
        <div class="form-group has-success">
          <label class="col-md-2 control-label" style="font-size: 17px"><b>Capacidade</b></label>
          <div class="col-md-10">
            <label class="control-label" style="font-size: 17px"><b>{$this->objDados->capacidade}</b></label>
          </div>
        </div>
        <div class="form-group has-success">
          <label class="col-md-2 control-label" style="font-size: 17px"><b>Unidade</b></label>
          <div class="col-md-10">
            <label class="control-label" style="font-size: 17px"><b>{$this->objDados->uncapac}</b></label>
          </div>
        </div>
HTML
      );

      $html->add($form);
      echo $html->generate();
    }
  }