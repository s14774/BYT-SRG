<html>
<head>
<title>Ścieżki Rowerowe Gdańska</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<style>
body {
	font-size: 12px;
}
</style>
</head>
<body>


<?php
do{
  $json = file_get_contents('http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath');
}while ($json == "");
$obj = (array) json_decode($json);
$data = [];
$labels = "";
$values = [];
foreach ($obj as $key => $value) {
  $a = (array)$value;
  $id = $a["id"];
  $name = $a["name"];
  $str = "http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath/".$id."/15minutes";
  do{
    $value = file_get_contents($str);
  }while ($value == "");
  print_r($id." ".$value." ".$name."<br/>");
  array_push($data, $value);
  if ($labels == ""){
    $labels.=$name;
  }else{
    $labels.="|".$name;
  }
  #echo "<br/>";
  array_push($values, $value);
}
#print_r($data);
#print_r($labels);
$max = max($values);
$aX = (int)($max / 200) * 20;
#print_r("Max: ".$max." ".$aX);
include_once('./LabChartsBar.php');

$LabChartsBar = new LabChartsBar();
$LabChartsBar->setData($data);
$LabChartsBar->setSize('1000x200');
$LabChartsBar->setTitle('Ruch z ostatnich 15 minut:');
$LabChartsBar->setColors('D9351C|FAAC02|79D81C|2A9DF0|02AA9D|00FFFF|FF00FF|BBBB00|0000FF|00FF00|FF0000');
$LabChartsBar->setLabels($labels);
$LabChartsBar->setBarStyles(40);
$LabChartsBar->setAxis($aX);
$LabChartsBar->setGrids($aX);

?>

<img src="<?=$LabChartsBar->getChart()?>">

</body>
</html>
