DIR="$( cd "$( dirname "${BASH_SOURCE[0]}" )" && pwd )"
#cd ..\data
#@java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server %1 %2 %3 %4 %5 %6 %7 %8 %9

cd $DIR/data
java -classpath ../lib/hsqldb.jar org.hsqldb.server.Server --database.0 mem:mydb --dbname.0 workdb
