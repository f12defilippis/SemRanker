cd semrankercommon
mvn clean install
cd ..
cd semrankerengine
mvn clean install
cd ..
cd semrankerweb
mvn clean install
cd ..
cp -rf semrankerengine/target/semrankerengine-0.0.1-SNAPSHOT.jar .
cp -rf semrankerengine/src/main/resources/application.properties .
cp -rf semrankerweb/target/semrankerweb-0.0.1-SNAPSHOT.jar .
cp -rf semrankerweb/src/main/resources/application.properties ./application-web.properties

