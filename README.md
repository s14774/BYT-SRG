. Należy zrobić paczkę za pomocą komendy
mvn package
. Należy uruchomić HSQL
ENV/hsqldb/runServer.sh
. Należy uruchomić GlassFish
ENV/glassfish4.1/bin/asadmin start-domain
. Należy zahostować projekt na serwerze glassfish
ENV/glassfish4.1/bin/asadmin deploy target/BYT-SRG.war

