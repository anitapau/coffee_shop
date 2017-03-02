
ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/coffeeShop/2
# heroku
#ENDPOINT=https://frozen-temple-15527.herokuapp.com/tcss360/coffeeshop/2
# local web runner deploy
# ENDPOINT=http://localhost:8080/tcss360/coffeeShop/1
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop/2
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop/2
curl -X DELETE -H "Content-Type: application/json" $ENDPOINT
echo
