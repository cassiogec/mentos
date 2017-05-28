<?php
  class DForm extends DBasicHtml
  {
    /**
     * @var bool
     */
    private $idGenerateTable = false;

    /**
     * DForm constructor.
     * @param string $mvcAction
     * @param bool $idGenerateTable
     * @param string $dsTitle
     */
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

    /**
     * @return string
     */
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

    /**
     * @param string $name
     * @param string $value
     * @return string
     */
    public static function getInputHidden($name = "", $value = "")
    {
      return "<input name=\"$name\" type=\"hidden\" value=\"$value\">";
    }

    /**
     * @param $type
     * @param string $label
     * @param string $name
     * @param string $dsPlaceHolder
     * @param string $value
     * @param string $dsHtmlExtra
     * @return string
     */
    public static function getInput($type, $label = "", $name = "", $dsPlaceHolder = "", $value = "", $dsHtmlExtra = "")
    {
      $name    = "form_{$name}";
      $dsLabel = self::getInputLabel($label);

      if (isset($_REQUEST[$name]) && strValue($_REQUEST[$name]))
        $value = $_REQUEST[$name];

      if (strValue($value))
        $value = "value=\"{$value}\"";

      return <<<HTML
        <div class="form-group has-success">
          {$dsLabel}
          <div class="col-md-10">
            <input required name="{$name}" type="{$type}" class="form-control" {$value} {$dsHtmlExtra} placeholder="{$dsPlaceHolder}">
          </div>
        </div>
HTML;
    }

    /**
     * @param string $dsLabel
     * @return string
     */
    public static function getSubmitButton($dsLabel = "Cadastrar")
    {
      return <<<HTML
        <div class="form-group">
          <div class="col-md-3 col-md-offset-2">
            <input type="submit" class="btn btn-raised btn-success" value="{$dsLabel}"><div class="ripple-container"></div>
          </div>
        </div>
HTML;
    }

    /**
     * @param string $label
     * @return string
     */
    private static function getInputLabel($label = "")
    {
      if (!strValue($label))
        return "";

      return <<<HTML
        <label class="col-md-2 control-label" style="font-size: 17px"><b>{$label}</b></label>
HTML;
    }

  }