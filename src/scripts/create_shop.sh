
# heroku
ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/coffeeShop
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/coffeeShop
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop

curl -X POST -H "Content-Type: application/json" -d '{
	"name": "coffee Brew",
	"street":"145th main",
	"city": "Chicago",
	"state":"WA",
	"zip":"98009",
	"opentime":9,
	"closetime":15,
	"phone":"3107793311",
	"description":"shop having sitting tables outside."
}' $ENDPOINT

echo
