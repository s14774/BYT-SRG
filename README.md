. Należy zrobić paczkę za pomocą komendy
mvn package
. Należy uruchomić HSQL
ENV/hsqldb/runServer.sh
. Należy uruchomić GlassFish
ENV/glassfish4.1/bin/asadmin start-domain
. Należy zahostować projekt na serwerze glassfish
ENV/glassfish4.1/bin/asadmin deploy target/BYT-SRG.war

. Panel zarządzający powinien być pod adresem
http://127.0.0.1:4848
. Aplikacja powinna być dostępna pod adresem
http://127.0.0.1:8080/BYT-SRG/
https://127.0.0.1:8181/BYT-SRG/ 
