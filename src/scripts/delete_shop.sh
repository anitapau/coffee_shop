
ENDPOINT=http://gentle-coast-59786.herokuapp.com/tcss360/coffeeshop
# heroku
#ENDPOINT=https://frozen-temple-15527.herokuapp.com/tcss360/coffeeshop
# local web runner deploy
ENDPOINT=http://localhost:8080/tcss360/coffeeshop
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop
curl -X POST -H "Content-Type: application/json" -d @./fred_shop.json $ENDPOINT
#curl -X POST -H "Content-type: application/json" -d '{"name":"Fred Smith"}' $ENDPOINT
echo
