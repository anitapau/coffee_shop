ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/review
#local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/users
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/users
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/users
curl -X GET -H "Content-Type: application/html" $ENDPOINT
echo
