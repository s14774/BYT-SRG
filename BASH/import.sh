#!/bin/bash

rest(){ curl -sH "Content-Type: application/json" -X $1 -d "$2" $3; echo; }
rest POST '{"name":"Grunwaldzka"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Slowackiego"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Partyzantow"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Wyspianskiego"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Sobieskiego"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"3 Maja"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Kartuska"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Armii Krajowej"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Lostowicka"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath
rest POST '{"name":"Jabloniowa"}' http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath

for id in {2..11}
do
  rest POST "@$id.bp.dump" "http://127.0.0.1:8080/BYT-SRG/rest/bicyclepath/$id/importMultiple";
done

