<?php
  class DBasicHtml
  {
    /**
     * @var string
     */
    protected $dsHtml = "";

    /**
     * @var array
     */
    protected $arrObjects = array();

    /**
     * DBasicHtml constructor.
     */
    public function __construct()
    {
      $this->dsHtml     = "";
      $this->arrObjects = array();
    }

    /**
     * @param $obj
     * @throws Exception
     */
    public function add($obj)
    {
      if ($obj instanceof DBasicHtml)
        $this->arrObjects[] = $obj;
      elseif (is_string($obj))
        $this->dsHtml .= $obj;
      else
        throw new Exception("Objeto inválido para gerar DBasioHtml");
    }

    /**
     * @return string
     */
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

    /**
     * @param $tag
     * @param $content
     * @param array $arrOptions
     * @return string
     * @throws Exception
     */
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
