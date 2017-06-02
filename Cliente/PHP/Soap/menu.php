<?php
  session_start();

  if (!isset($_SESSION["s_ds_ip"]))
    $_SESSION["s_ds_ip"] = "127.0.0.1";

  require_once("includes.php");
  $Controller = new MentosSoapController();