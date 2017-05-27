<?php
  class DForm extends DBasicHtml
  {
    private $idGenerateTable = false;

    public function __construct($mvcAction = "", $idGenerateTable = false, $dsTitle = "")
    {
      $this->idGenerateTable = $idGenerateTable;
      parent::__construct();

      if ($this->idGenerateTable)
      {
        $this->dsHtml .= <<<HTML
          <div class="container-fluid main">
            <div class="col-md-6 col-md-offset-1">
              <div class="well bs-component">
                <form action="menu.php" method="GET">
                  <fieldset>
                    <legend>{$dsTitle}</legend>
HTML;
      }
      else
      {
        $this->dsHtml .= <<<HTML
          <form action="menu.php" method="GET">
HTML;
      }

      if (strValue($mvcAction))
        $this->add(self::getInputHidden("action", $mvcAction));
    }

    public function generate()
    {
      parent::generate();

      if ($this->idGenerateTable)
      {
        $this->dsHtml .= <<<HTML
                  </fieldset>
                </form>
              </div>
            </div>
          </div>
HTML;
      }
      else
        $this->idGenerateTable .= "</form>";

      return $this->dsHtml;
    }

    public static function getInputHidden($name = "", $value = "")
    {
      return "<input name=\"$name\" type=\"hidden\" value=\"$value\">";
    }

    public static function getInput($type, $label = "", $name = "", $dsPlaceHolder = "", $value = "")
    {
      $dsLabel = self::getInputLabel($label);

      if (strValue($value))
        $value = "value=\"{$value}\"";

      return <<<HTML
        <div class="form-group has-success">
          {$dsLabel}
          <div class="col-md-10">
            <input name="form_{$name}" type="{$type}" class="form-control" {$value} placeholder="{$dsPlaceHolder}">
          </div>
        </div>
HTML;
    }

    private static function getInputLabel($label = "")
    {
      if (!strValue($label))
        return "";

      return <<<HTML
        <label class="col-md-2 control-label" style="font-size: 17px"><b>{$label}</b></label>
HTML;
    }

  }