#!/bin/bash

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
randomizer(){ q=$1; i=$((q+(RANDOM%5)-2)); if (("$i"<"0")); then i="0"; fi; echo "$i";return $i; }
path2app="http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath"
weightsBR=()
for i in {1..50}; do weightsBR+=($(((RANDOM%7)+3))); done;
#         0 1 2 3 4 5 6 7 8  9 0 1 2 3 4 5 6  7 8 9 0 1 2 3 
weightsH=(3 2 1 1 1 3 7 9 10 7 6 5 5 6 7 8 10 8 7 6 5 4 3 3)     
probabilityNumber="20" #Bigger is less 
amplitude="2" #Bigger is less
otime="0"

while true; do
  time=$(date +"%s")
  if (($time >= $otime + $amplitude));
  then otime=$time;
  else sleep 1; continue;
  fi
  
  h=$(date +"%H")
  listaId=$(rest GET "" "$path2app" | jq '.[]["id"]')
  for id in $listaId;
  do
    echo "!id $id"
    echo "!we ${weightsBR[$id]}"
    rn=$(randomizer ${weightsBR[$id]})
    rnh=$((rn*${weightsH[$h]}/10))
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
