# Uruchamianie
1. Należy zrobić paczkę za pomocą komendy
```
mvn package
```
2. Należy uruchomić HSQL
```
ENV/hsqldb/runServer.sh
```
3. Należy uruchomić GlassFish
```
ENV/glassfish4.1/bin/asadmin start-domain
```
4. Należy zahostować projekt na serwerze glassfish
```
ENV/glassfish4.1/bin/asadmin deploy target/BYT-SRG.war
```

# Zarządzanie
Panel zarządzający powinien być pod adresem
```
http://127.0.0.1:4848
```
Aplikacja powinna być dostępna pod adresem
```
http://127.0.0.1:8080/BYT-SRG/
https://127.0.0.1:8181/BYT-SRG/
```

# Wyłączanie
Usuwanie aplikacji z GlassFisha 
```
ENV/glassfish4.1/bin/asadmin undeploy BYT-SRG
```
Wyłączanie serwera GlassFish
```
ENV/glassfish4.1/bin/asadmin stop-domain
```
