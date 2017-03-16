heroku run echo $\JDBC_DATABASE_URL >.mydburl 2>.mydburlerr
myurl=`cat .mydburl`
if [[ -z $myurl ]]
then
  heroku pg:credentials > .mydburl
  thing=`tail -n 1 .mydburl`
  usr=`echo $thing | cut -d'/' -f 3 | cut -d':' -f 1`
  pswd=`echo $thing | cut -d'/' -f 3 | cut -d':' -f 2 | cut -d'@' -f 1`
  url=`echo $thing | cut -d'/' -f 3 | cut -d':' -f 2 | cut -d'@' -f 2`
  db=`echo $thing | cut -d'/' -f 4`
  port=`echo $thing | cut -d':' -f 4 | cut -d'/' -f 1`
  echo "usr=$usr"
  echo "pswd=$pswd"
  echo "url=$url"
  echo "db=$db"
  echo "port=$port"
  myurl="jdbc:postgresql://ec2-54-235-204-221.compute-1.amazonaws.com:5432/d19m0j1erhvr7v?user=wxojhmodfpbmsv&password=80cfef5defecd78ff44e5e2bab48a26b06f930d1f57e097a6be957be98358c53&sslmode=require"
fi
export JDBC_DATABASE_URL="$myurl"
echo "Run application with $JDBC_DATABASE_URL DB URL."
java -jar target/dependency/webapp-runner.jar --port 8084 target/coffeeshop-1.0-SNAPSHOT.war
