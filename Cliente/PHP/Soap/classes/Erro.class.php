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
     * @var int
     */
    private $idStatus;

    const STATUS_OK      = 0;
    const STATUS_WARNING = 1;
    const STATUS_ERROR   = 2;

    /**
     * Erro constructor.
     */
    public function __construct()
    {
      $this->dsError   = "";
      $this->dsWarning = "";
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
     * @param int $idStatus
     * @return bool
     */
    public function checkStatus($idStatus = self::STATUS_OK)
    {
      return ($this->idStatus === $idStatus);
    }
  }