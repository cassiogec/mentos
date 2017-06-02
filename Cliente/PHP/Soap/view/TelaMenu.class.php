<?php
  class TelaMenu extends Tela
  {
    /**
     * Método para Montar a Tela
     */
    protected function montar()
    {
      $html = new DHtml("Inicio");
      $html->addWelcome();

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      if ($this->Erro->checkStatus(Erro::STATUS_INFO))
        $html->addAlertMessage($this->Erro->getInfo(), "info", false);

      if (isset($_REQUEST["info_message"]) && strValue($_REQUEST["info_message"]))
        $html->addAlertMessage($_REQUEST["info_message"], "info", false);

      $js = <<<JS
        $(document).ready(function(){
          $('#tipoFiltroMenu').change(function(){
            $('.filtroMenu').hide();
          
            if ($(this).val() == 'codigo')
              $('input[name=form_cd_veiculo]').show();
            else if ($(this).val() == 'placa')
              $('input[name=form_ds_placa]').show();
            else if ($(this).val() == 'tipo')
              $('select[name=form_id_tipo]').show();
          });
        });
JS;

      $html->addJS($js);

      $table = new DTable("Listagem de Veículos");

      $dsOptions  =
        "<select name='form_id_tipo' class='form-control filtroMenu' style='display: none;'><option value='' selected disabled>Selecione um tipo!</option>";

      foreach (obterTipoVeiculo() AS $val => $desc)
        $dsOptions .= "<option value='$val'>$desc</option>";

      $dsOptions .= "</select>";

      $table->add(<<<HTML
        <div class="col-md-6">
          <div class="col-md-4">
            <div class="form-group">
              $dsOptions
              <input type="text"   name="form_ds_placa"   class='form-control filtroMenu' placeholder="Digite a Placa"  style="display: none;">
              <input type="number" name="form_cd_veiculo" class='form-control filtroMenu' placeholder="Digite o Código" style="display: none;">
            </div>
          </div>
          <div class="col-md-4">
            <div class="form-group">
              <select name='form_id_tipo_filtro' id="tipoFiltroMenu" class='form-control' required>
                <option value='' selected disabled>Filtro</option>
                <option value='codigo'>Código</option>
                <option value='placa'>Placa</option>
                <option value='tipo'>Tipo</option>
              </select>
            </div>
          </div>
          <div class="col-md-2">
            <div class="form-group">
              <input type="submit" value="Pesquisar" class="btn btn-raised btn-info"><div class="ripple-container"></div></input>   
            </div>
          </div>
        </div>
HTML
     );

      $table->add(DBasicHtml::get("thead", DBasicHtml::get("tr", array(
        DBasicHtml::get("th", "Código"),
        DBasicHtml::get("th", "Placa"),
        DBasicHtml::get("th", "Tipo"),
        DBasicHtml::get("th", "Capacidade"),
        DBasicHtml::get("th", "Unidade"),
        DBasicHtml::get("th", ""),
        DBasicHtml::get("th", ""),
        DBasicHtml::get("th", ""),
        DBasicHtml::get("th", ""),
      ))));

      if (is_array($this->objDados) && count($this->objDados))
      {
        $table->add("<tbody>");

        foreach ($this->objDados AS $dados)
        {
          $urlConsultar   = getDsUrl("consultarVeiculo",   array("form_cd_veiculo" => $dados->codigo));
          $urlAlterar     = getDsUrl("manutencaoVeiculo",  array("form_cd_veiculo" => $dados->codigo));
          $urlExcluir     = getDsUrl("excluirVeiculo",     array("form_cd_veiculo" => $dados->codigo));
          $urlLocalizacao = getDsUrl("localizacaoVeiculo", array("form_cd_veiculo" => $dados->codigo));

          $table->add(DBasicHtml::get("tr", array(
            DBasicHtml::get("td", $dados->codigo),
            DBasicHtml::get("td", $dados->placa),
            DBasicHtml::get("td", $dados->dsTipo),
            DBasicHtml::get("td", $dados->capacidade),
            DBasicHtml::get("td", $dados->uncapac),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"{$urlConsultar}\"   class=\"btn btn-block btn-basic\">Consultar</a>"),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"{$urlLocalizacao}\" class=\"btn btn-block btn-info\">Localização</a>"),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"{$urlAlterar}\"     class=\"btn btn-block btn-success\">Alterar</a>"),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"{$urlExcluir}\"     class=\"btn btn-block btn-danger\">Excluir</a>"),
          )));
        }

        $table->add("</tbody>");
      }
      else
        $table->add(DBasicHtml::get("tbody", "<tr><td colspan='7'><i>Sem Dados</i></td></tr>"));

      $form = new DForm("listarTipo");
      $form->add($table);
      $html->add($form);

      echo $html->generate();
    }
  }