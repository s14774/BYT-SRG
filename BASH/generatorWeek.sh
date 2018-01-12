#!/bin/bash

dataTeraz=$(date +"%s")
dataTygTemu=$((dataTeraz - (60*60*24*7)))

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
path2app="http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath"
#for i in {1..50}; do echo "$i: ${weightsBR[$i]}"; done
#         0 1 2 3 4 5 6 7 8 9 0 1 2 3 4 5 6 7 8 9 0 1 2 3 
weightsH=(3 2 1 1 1 3 7 9 9 7 6 5 5 6 7 8 9 8 7 6 5 4 3 3)     
probabilityNumber="10" #Bigger is less 
amplitude="1800" #Bigger is less

dataTmp=$dataTygTemu
while true; do
  dataTmp=$((dataTmp+amplitude))
  if (($dataTmp >= $dataTeraz));
  then break;
  fi
  date -d@$dataTmp
  pos="{\"date\":\"$(date +"%FT%T%:z" -d@$dataTmp)\"}"

  h=$(date +"%k" -d@$dataTmp)
  listaId=$(rest GET "" "$path2app" | jq '.[]["id"]')
  for id in 2;
  do
    #echo "!id $id" >&2
    #echo "!we ${weightsBR[$id]}" >&2
    rnh=${weightsH[$h]}
    #echo "!rnh $rnh" >&2
    #echo "!rn2 $rn2" >&2
    for (( i=0; i<$rnh; i++))
    do
      echo -n "OK$id " >&2
      post="[$(for (( i=1; i<((200-100+($RANDOM%100))); i++ )); do echo -n "$pos,"; done; echo -n "$pos")]"
      rest POST "$post" "$path2app/$id/importMultiple"
    done
    #echo >&2
  done
done
