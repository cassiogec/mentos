<!DOCTYPE html>
<!--
To change this license header, choose License Headers in Project Properties.
To change this template file, choose Tools | Templates
and open the template in the editor.
-->
<html>
    <head>
        <meta charset="UTF-8">
        <title></title>
        
        <meta http-equiv="X-UA-Compatible" content="IE=edge">

  <!-- Mobile support -->
  <meta name="viewport" content="width=device-width, initial-scale=1">
        
  <script src="//code.jquery.com/jquery-1.10.2.min.js"></script>
  
  <link href="https://developers.google.com/maps/documentation/javascript/demos/demos.css" rel="stylesheet">
  
  <style>
      html, body {
        height: 100%;
        margin: 0;
        padding: 0;
      }
      #map {
        height: 100%;
      }
    </style>
    
    <script src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDNoLvKMx7BaqWCsQLi7XTe7V5fEDHISG4&callback=initMap" async defer></script>
        
        <script type="text/javascript">
            var map;
            function initMap() {
              map = new google.maps.Map(document.getElementById('map'), {
                center: {lat: -34.397, lng: 150.644},
                zoom: 8
              });
            }
 
    </script>
    <body>
        <div style="width:900px;height:900px;" class="teste">
   <div id="map"></div>
        </div>
    </body>
</html>