<?php
  class TelaMenu extends Tela
  {
    protected function montar()
    {
      $html = new DHtml("Inicio");
      $html->addWelcome();

      if ($this->Erro->checkStatus(Erro::STATUS_ERROR))
        $html->addAlertMessage($this->Erro->getError());

      if ($this->Erro->checkStatus(Erro::STATUS_WARNING))
        $html->addAlertMessage($this->Erro->getWarning(), "warning");

      $table = new DTable("Listagem de Veículos");

      $table->add(<<<HTML
        <div class="col-md-6">
          <div class="col-md-6">
            <div class="form-group">
              <input required name="form_id_tipo" type="number" class="form-control" placeholder="Digite um tipo!">
            </div>
          </div>
          <div class="col-md-6">
            <div class="form-group">
              <input type="submit" value="Pesquisar por Tipo" class="btn btn-raised btn-info"><div class="ripple-container"></div></input>   
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
      ))));

      if (is_array($this->objDados) && count($this->objDados))
      {
        $table->add("<tbody>");

        foreach ($this->objDados AS $dados)
        {
          $table->add(DBasicHtml::get("tr", array(
            DBasicHtml::get("td", $dados->codigo),
            DBasicHtml::get("td", $dados->placa),
            DBasicHtml::get("td", $dados->tipo),
            DBasicHtml::get("td", $dados->capacidade),
            DBasicHtml::get("td", $dados->uncapac),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"javascript:void(0)\" class=\"btn btn-block btn-info\">Localização</a>"),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"javascript:void(0)\" class=\"btn btn-block btn-success\">Alterar</a>"),
            DBasicHtml::get("td", "<a style=\"margin:0px; \" href=\"javascript:void(0)\" class=\"btn btn-block btn-danger\">Excluir</a>"),
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