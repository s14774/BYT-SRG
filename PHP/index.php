<?php
  $page = $_SERVER['PHP_SELF'];
  $sec = "3";
?>
<html>
<head>
<title>Ścieżki Rowerowe Gdańska</title>
<meta http-equiv="refresh" content="<?php echo $sec?>;URL='<?php echo $page?>'">
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
  font-size: 12px;
}
table {
  font-size: 12px;
}
</style>
</head>
<body>
<table>

<?php
#BAR + PIE
do{
  $json = file_get_contents('http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath');
}while ($json == "");
$obj = (array) json_decode($json);
$data = [];
$labels = "";
#$values = [];
$colors = 'D9351C|FAAC02|79D81C|2A9DF0|02AA9D|00FFFF|FF00FF|BBBB00|0000FF|00FF00|FF0000';
foreach ($obj as $key => $value) {
  $a = (array)$value;
  $id = $a["id"];
  $name = $a["name"];
  $str = "http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath/".$id."/15minutes";
  do{
    $value = file_get_contents($str);
  }while ($value == "");
  print_r("<tr><th>".$id."</th><th>".$value."</th><th>".$name."</th></tr>");
  array_push($data, $value);
  if ($labels == ""){
    $labels.=$name;
  }else{
    $labels.="|".$name;
  }
  #echo "<br/>";
  #array_push($values, $value);
}
$max = max($data);
$aX = (int)($max / 200) * 20;
if($aX < 20)
  $aX = 20; 
#END BAR + PIE
#LINE
do{
  $json = file_get_contents('http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath');
}while ($json == "");
#echo "JSON: \"$json\"";
if($json!="[]"){
  $linedata = [];
  $linelabels = "";
  $json2 = file_get_contents('http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath/2/week');
  $obj = (array)json_decode($json2);
  foreach ($obj as $key => $value) {
  echo "<pre>";
    $a = (array)$value;
    array_push($linedata,$a["quantity"]);
    $hour = $a["hour"];
    if ($linelabels == ""){
      $linelabels=($hour%3 == 0 ? $hour%24 : " "); #$hour%24;
    }else{
      $linelabels.="|".($hour%3 == 0 ? $hour%24 : "");
    }
  }
  $linemax = max($linedata);
  $lineaX = (int)($linemax / 200) * 20;
  if($lineaX < 20)
  $lineaX = 20; 
}
#END LINE

include_once('./LabChartsBar.php');
include_once('./LabChartsLine.php');
include_once('./LabChartsPie.php');

$LabChartsBar = new LabChartsBar();
$LabChartsBar->setData($data);
$LabChartsBar->setSize('1000x300');
$LabChartsBar->setTitle('Ruch z ostatnich 15 minut:');
$LabChartsBar->setColors($colors);
$LabChartsBar->setLabels($labels);
$LabChartsBar->setBarStyles(40);
$LabChartsBar->setAxis($aX);
$LabChartsBar->setGrids($aX);

$LabChartsPie = new LabChartsPie();
$LabChartsPie->setData($data);
$LabChartsPie->setType('p3');
$LabChartsPie->setTitle('Ruch z ostatnich 15 minut:');
$LabChartsPie->setSize('600x300');
$LabChartsPie->setColors($colors);
$LabChartsPie->setLabels($labels);

$LabChartsLine = new LabChartsLine();
$LabChartsLine->setData($linedata);
$LabChartsLine->setColors('D9351C');
$LabChartsLine->setSize('1000x250');
$LabChartsLine->setTitle('Ruch z ostatniego tygodnia x godzin temu');
$LabChartsLine->setAxis ($lineaX, $linelabels);
$LabChartsLine->setGrids ($lineaX);
?>
</table>
<img src="<?=$LabChartsBar->getChart()?>">
<img src="<?=$LabChartsPie->getChart()?>">
<img src="<?=$LabChartsLine->getChart()?>">
</body>
</html>
