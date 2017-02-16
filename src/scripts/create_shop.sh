
ENDPOINT=http://gentle-coast-59786.herokuapp.com/tcss360/coffeeshop
# heroku
#ENDPOINT=https://frozen-temple-15527.herokuapp.com/tcss360/coffeeshop
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/coffeeShop
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop1","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop2","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop3","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop4","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop5","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop6","description": "seattle downtown","address": "123 test"}' $ENDPOINT
curl -X POST -H "Content-Type: application/json" -d '{"name": "Shop7","description": "seattle downtown","address": "123 test"}' $ENDPOINT
echo
