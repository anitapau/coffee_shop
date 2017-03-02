ENDPOINT=https://gentle-coast-59786.herokuapp.com/tcss360/review
# local web runner deploy
#ENDPOINT=http://localhost:8080/tcss360/review
# net beans deploy
#ENDPOINT=http://localhost:8084/sample_maven_web_app/tcss360/review
# manual deploy
#ENDPOINT=http://localhost:8080/sample_maven_web_app-1.0-SNAPSHOT/tcss360/review
                # you cannot update the review id
curl -X PUT -H "Content-Type: application/json" -d '{"review": "It is good", "shopid": 1,"rating":4}' $ENDPOINT
echo
