<?php
  function ppr($vl)
  {
    echo "<pre>";
    print_r($vl);
    echo "</pre>";
  }

  function strValue($vl)
  {
    return ($vl !== null && $vl !== "" && $vl !== false);
  }