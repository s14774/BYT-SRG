#!/bin/bash

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
path2app="http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath"
listaId=$(rest GET "" "$path2app" | jq '.[]["id"]')
for id in $listaId
do
  rest GET '' "$path2app/$id/all" | perl -pe 's|{"bicyclePath.+?("date":".+?").*?}|{\1}|g' > $id.bp.dump
done

