java -jar semrankerengine-0.0.1-SNAPSHOT.jar --spring.config.location=./application.properties >> console.log 2>&1 &
java -jar semrankerweb-0.0.1-SNAPSHOT.jar --spring.config.location=./application-web.properties >> console-web.log 2>&1 & 
