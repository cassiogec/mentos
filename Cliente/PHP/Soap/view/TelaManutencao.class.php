<?php
  class TelaManutencao extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    public function montar()
    {
      if (isset($this->objDados) && is_object($this->objDados))
      {
        $cdVeiculo    = $this->objDados->codigo;
        $dsPlaca      = formataPlaca($this->objDados->placa, "sys");
        $idTipo       = $this->objDados->tipo;
        $vlCapacidade = $this->objDados->capacidade;
        $dsUnidade    = $this->objDados->uncapac;
        $action       = "alterarVeiculo";
        $dsButton     = "Alterar";
      }
      else
      {
        $cdVeiculo = $dsPlaca = $idTipo = $vlCapacidade = $dsUnidade = "";
        $action    = "adicionarVeiculo";
        $dsButton  = "Cadastrar";
      }

      if (strValue($cdVeiculo))
        $dsTitle = "Manutenção de Veículo";
      else
        $dsTitle = "Cadastro de Veículo";

      $html = new DHtml($dsTitle);

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $form = new DForm($action, true, $dsTitle);

      if (strValue($cdVeiculo))
      {
        $form->add(<<<HTML
          <div class="form-group has-success">
            <label class="col-md-2 control-label" style="font-size: 17px"><b>Código</b></label>
            <div class="col-md-10">
              <label class="control-label" style="font-size: 17px"><b>{$cdVeiculo}</b></label>
            </div>
          </div>
HTML
        );

        $form->add(DForm::getInputHidden("form_cd_veiculo", $cdVeiculo));
      }

      $arOptions = obterTipoVeiculo();

      $form->add(DForm::getInput("text",      "Placa",      "ds_placa",      "Digite a Placa",                  $dsPlaca,      "mascara=\"placa\""));
      $form->add(DForm::getSelect($arOptions, "Tipo",       "id_tipo",       "Selecione um Tipo",               $idTipo));
      $form->add(DForm::getInput("number",    "Capacidade", "vl_capacidade", "Informe o Valor da Capacidade",   $vlCapacidade, "mascara=\"numero\""));
      $form->add(DForm::getInput("text",      "Unidade",    "ds_unidade",    "Informe a Unidade de Capacidade", $dsUnidade,    "mascara=\"unidade\""));
      $form->add(DForm::getSubmitButton($dsButton));
      $html->add($form);

      echo $html->generate();
    }
  }
