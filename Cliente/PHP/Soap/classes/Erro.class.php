<?php
  class Erro
  {
    /**
     * @var string
     */
    private $dsError;

    /**
     * @var string
     */
    private $dsWarning;

    /**
     * @var string
     */
    private $dsInfo;

    /**
     * @var int
     */
    private $idStatus;

    const STATUS_OK      = 0;
    const STATUS_WARNING = 1;
    const STATUS_ERROR   = 2;
    const STATUS_INFO    = 3;

    /**
     * Erro constructor.
     */
    public function __construct()
    {
      $this->dsError   = "";
      $this->dsWarning = "";
      $this->dsInfo    = "";
      $this->idStatus  = self::STATUS_OK;
    }

    /**
     * @param $dsError
     */
    public function setError($dsError)
    {
      $this->dsError  = $dsError;
      $this->idStatus = self::STATUS_ERROR;
    }

    /**
     * @param $dsWarning
     */
    public function setWarning($dsWarning)
    {
      $this->dsWarning = $dsWarning;
      $this->idStatus  = self::STATUS_WARNING;
    }

    /**
     * @param $dsInfo
     */
    public function setInfo($dsInfo)
    {
      $this->dsInfo   = $dsInfo;
      $this->idStatus = self::STATUS_INFO;
    }

    /**
     * @return string
     */
    public function getError()
    {
      return $this->dsError;
    }

    /**
     * @return string
     */
    public function getWarning()
    {
      return $this->dsWarning;
    }

    /**
     * @return string
     */
    public function getInfo()
    {
      return $this->dsInfo;
    }

    /**
     * @param int $idStatus
     * @return bool
     */
    public function checkStatus($idStatus = self::STATUS_OK)
    {
      return ($this->idStatus === $idStatus);
    }
  }