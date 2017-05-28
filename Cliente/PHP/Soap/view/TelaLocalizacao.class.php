<?php
  class TelaLocalizacao extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    protected function montar()
    {
      $html = new DHtml("Localização");
      $html->addTitle("Dados do Veículo", array(
        "Código: "     . $this->objDados->dadosVeiculo->codigo,
        "Placa: "      . $this->objDados->dadosVeiculo->placa,
        "Tipo: "       . $this->objDados->dadosVeiculo->dsTipo,
        "Capacidade: " . $this->objDados->dadosVeiculo->capacidade,
        "Unidade: "    . $this->objDados->dadosVeiculo->uncapac
      ));

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $table = new DTable("Localizações do Veículo");

      $value = function($vl) {
        if (isset($vl) && strValue($vl))
          return "value=\"$vl\"";
        else
          return "";
      };

      $js = <<<JS
        function strValue(v)
        {
          return (v !== undefined && v !== null && v !== '');
        }
      
        $(document).ready(function(){
          $('input[type=submit]').click(function() {
            var dtInicio = $('#dtInicio').val();
            var tmInicio = $('#tmInicio').val();            
            var dtFinal  = $('#dtFinal').val();
            var tmFinal  = $('#tmFinal').val();
            
            if ((strValue(dtInicio) && !strValue(dtFinal)) || (!strValue(dtInicio) && strValue(dtFinal)))
            {
              alert('Para filtrar por Data é necessário preencher o início e o fim!');
              return false;
            }
            
            if ((strValue(dtInicio) && !strValue(tmInicio)) || (!strValue(dtInicio) && strValue(tmInicio)))
            {
              alert('É necessário preencher a data e hora de início!');
              return false;            
            }
            
            if ((strValue(dtFinal) && !strValue(tmFinal)) || (!strValue(dtFinal) && strValue(tmFinal)))
            {
              alert('É necessário preencher a data e hora final!');
              return false;            
            }
            
            if (dtInicio > dtFinal)
            {
              alert('A data de início deve ser menor ou igual à data final!');
              return false;
            }
            
            if (dtInicio <= dtFinal && tmInicio > tmFinal)
            {
              alert('A data de início deve ser menor ou igual à data final!');
              return false;
            }
          });
        });
JS;

      $html->addJS($js);


      $table->add(<<<HTML
        <div class="form-group has-success">
          <label class="col-md-1 control-label" style="font-size: 17px"><b>Data Início</b></label>
          <div class="col-md-2">
            <input name="form_dt_inicio" id="dtInicio" type="date" class="form-control" {$value($_REQUEST["form_dt_inicio"])}>
          </div>
          <div class="col-md-2">
            <input name="form_tm_inicio" id="tmInicio" type="time" class="form-control" {$value($_REQUEST["form_tm_inicio"])}>
          </div>
          <label class="col-md-1 control-label" style="font-size: 17px"><b>Data Final</b></label>
          <div class="col-md-2">
            <input name="form_dt_final" id="dtFinal" type="date" class="form-control" {$value($_REQUEST["form_dt_final"])}>
          </div>
          <div class="col-md-2">
            <input name="form_tm_final" id="tmFinal" type="time" class="form-control" {$value($_REQUEST["form_tm_final"])}>
          </div>
          <div class="col-md-1">
            <input type="submit" class="btn btn-raised btn-success" value="Pesquisar"><div class="ripple-container"></div>
          </div>
        </div>
HTML
      );

      $table->add(DBasicHtml::get("thead", DBasicHtml::get("tr", array(
        DBasicHtml::get("th", "Data"),
        DBasicHtml::get("th", "Latitude"),
        DBasicHtml::get("th", "Longitude")
      ))));

      if (is_array($this->objDados->arrLocalizacao) && count($this->objDados->arrLocalizacao))
      {
        $table->add("<tbody>");

        foreach ($this->objDados->arrLocalizacao AS $dados)
        {
          $table->add(DBasicHtml::get("tr", array(
            DBasicHtml::get("td", $dados->datahora),
            DBasicHtml::get("td", $dados->latitude),
            DBasicHtml::get("td", $dados->longitude)
          )));
        }

        $table->add("</tbody>");
      }
      else
        $table->add(DBasicHtml::get("tbody", "<tr><td colspan='7'><i>Sem Dados</i></td></tr>"));

      $form = new DForm("localizacaoVeiculo");
      $form->add(DForm::getInputHidden("form_id_submitted", 1));
      $form->add(DForm::getInputHidden("form_cd_veiculo",   $this->objDados->dadosVeiculo->codigo));
      $form->add($table);
      $html->add($form);

      echo $html->generate();
    }
  }