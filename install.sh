cd SemRanker/semrankercommon
mvn clean install
cd ../..
cd SemRanker/semrankerengine
mvn clean install
cd ../..
cp -rf SemRanker/semrankerengine/target/semrankerengine-0.0.1-SNAPSHOT.jar .
cp -rf SemRanker/semrankerengine/src/main/resources/application.properties .
