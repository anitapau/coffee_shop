
ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/coffeeshop
# heroku
#ENDPOINT=https://frozen-temple-15527.herokuapp.com/tcss360/users
# local web runner deploy
ENDPOINT=http://localhost:8080/tcss360/cofeeshop
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/users
curl -X POST -H "Content-Type: application/json" -d @./review.json $ENDPOINT
#curl -X POST -H "Content-type: application/json" -d '{"name": "Michael", "reivew": "sucks ass", "rating": -1}' $ENDPOINT
echo
