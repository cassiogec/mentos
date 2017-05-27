<?php
  class DBasicHtml
  {
    protected $dsHtml = "";

    protected $arrObjects = array();

    public function __construct()
    {
      $this->dsHtml     = "";
      $this->arrObjects = array();
    }

    public function add($obj)
    {
      if ($obj instanceof DBasicHtml)
        $this->arrObjects[] = $obj;
      elseif (is_string($obj))
        $this->dsHtml .= $obj;
      else
        throw new Exception("Objeto inválido para gerar DBasioHtml");
    }

    public function generate()
    {
      if (is_array($this->arrObjects) && count($this->arrObjects) > 0)
      {
        /** @var DHtml $obj */
        foreach ($this->arrObjects AS $obj)
          $this->dsHtml .= $obj->generate();
      }

      return $this->dsHtml;
    }

    public static function get($tag, $content, $arrOptions = array())
    {
      if (is_array($content))
      {
        $aux     = $content;
        $content = "";

        foreach ($aux AS $c)
        {
          if ($c instanceof DBasicHtml)
            $content .= $c->generate();
          else
            $content .= $c;
        }
      }

      $dsOptions = "";

      foreach ($arrOptions AS $k => $v)
        $dsOptions .= "$k=\"$v\" ";

      $ht = new self();
      $ht->add("<{$tag} {$dsOptions}>{$content}</{$tag}>");
      return $ht->generate();
    }
  }
