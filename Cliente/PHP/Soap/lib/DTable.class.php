<?php
  class DTable extends DBasicHtml
  {
    /**
     * DTable constructor.
     * @param string $dsTitle
     */
    public function __construct($dsTitle = "")
    {
      parent::__construct();

      $this->dsHtml = <<<HTML
        <div class="col-md-10 col-md-offset-1">
          <div class="panel panel-default">
HTML;

      if (strValue($dsTitle))
        $this->dsHtml .= "<div class=\"panel-heading\"><b>{$dsTitle}</b></div>";

      $this->dsHtml .= "<table class=\"table\">";
    }

    /**
     * @return string
     */
    public function generate()
    {
      parent::generate();
      $this->dsHtml .= <<<HTML
            </table>
          </div>
        </div>
HTML;

      return $this->dsHtml;
    }
  }