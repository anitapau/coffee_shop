
ENDPOINT=http://gentle-coast-59786.herokuapp.com/tcss360/coffeeshop
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/coffeeShop
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop
curl -X PUT -H "Content-Type: application/json" -d '{"name": "Updated Shop","description": "Updated seattle downtown","address": "Updated 123 test", "id":3}' $ENDPOINT
echo
