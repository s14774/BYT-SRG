#!/bin/bash

dataTeraz=$(date +"%s")
dataTygTemu=$((dataTeraz - (24*7)))

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
path2app="http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath"
weightsBR=()
for i in {1..50}; do weightsBR+=($(((RANDOM%7)+3))); done;
#         0 1 2 3 4 5 6 7 8  9 0 1 2 3 4 5 6  7 8 9 0 1 2 3 
weightsH=(3 2 1 1 1 3 7 9 10 7 6 5 5 6 7 8 10 8 7 6 5 4 3 3)     
probabilityNumber="10" #Bigger is less 
amplitude="2" #Bigger is less

dataTmp=$dataTygTemu
echo -n '['
while true; do
  dataTmp=$((dataTmp+amplitude))
  if (($dataTmp >= $dataTeraz));
  then break;
  fi
  
  post="{\"date\":\"$(date +"%FT%T%:z" -d@$dataTmp)\"}"

  h=$(date +"%H" -d@$dataTmp)
  listaId=$(rest GET "" "$path2app" | jq '.[]["id"]')
  for id in 2;
  do
    #echo "!id $id" >&2
    #echo "!we ${weightsBR[$id]}" >&2
    rn=$(${weightsBR[$id]})
    rnh=$((rn*${weightsH[$h]}/10))
    #echo "!rn $rn" >&2
    #echo "!rn2 $rn2" >&2
    rno=$((rn-rn2))
    for (( i=0; i<$rno; i++))
    do
      #echo -n "OK$id " >&2
      echo -n "$post,"
    done
    #echo >&2
  done
done
echo "$post]"
