#! /bin/bash
echo '**************Test Caesar*****************'
javac ./../../lib/Java/Caesar.java  ./../../lib/Java/CaesarExploits.java
javac -cp bin/junit.jar:bin/org.hamcrest.core_1.1.0.v20090501071000.jar:./bin CaesarTest.java ../../lib/Java/Caesar.java ../../lib/Java/CaesarExploits.java
mv ../../lib/Java/Caesar.class ./bin/
mv ../../lib/Java/CaesarExploits.class ./bin/
mv ./CaesarTest.class ./bin/
cd ./bin/
java -cp junit.jar:org.hamcrest.core_1.1.0.v20090501071000.jar:./ org.junit.runner.JUnitCore CaesarTest
echo '**************Test Caesar*****************'
