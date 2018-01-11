#!/bin/bash

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
randomizer(){ q=$1; i=$((q+(RANDOM%5)-2)); if (("$i"<"0")); then i="0"; fi; echo "$i";return $i; }
path2app="http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath"
weights=()
for i in {1..50}; do weights+=($(((RANDOM%7)+3))); done;
probabilityNumber="15"
amplitude="2"
otime="0"

while true; do
  time=$(date +"%s")
  if (($time >= $otime + $amplitude));
  then otime=$time;
  else sleep 1; continue;
  fi
  echo "!";
  
  listaId=$(rest GET "" "$path2app" | jq '.[]["id"]')
  for id in $listaId;
  do
    echo "!id $id"
    echo "!we ${weights[$id]}"
    rn=$(randomizer ${weights[$id]})
    echo "!rn $rn"
    rn2=$((RANDOM%probabilityNumber))
    echo "!rn2 $rn2"
    rno=$((rn-rn2))
    for (( i=0; i<$rno; i++))
    do
      echo -n "OK$id "
      rest POST '{}' "$path2app/$id"
    done
    echo 
  done
done
