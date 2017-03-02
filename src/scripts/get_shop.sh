
ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/coffeeShop/api/shops
# local web runner deploy
# ENDPOINT=http://localhost:8080/tcss360/coffeeShop/api/shops
# net beans deploy
# ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/coffeeshop/api/shops
# manual deploy
# ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/coffeeshop/api/shops
curl -X GET -H "Content-Type: application/html" $ENDPOINT
echo
