#!/usr/bin/env bash
cd `dirname $0`
echo '**************Test Caesar*****************'
javac ./../../lib/Java/Caesar.java ./../../lib/Java/CaesarExploits.java ./../../etc/Alphabet/Java/Alphabet.java
javac -cp bin/junit.jar:bin/org.hamcrest.core_1.1.0.v20090501071000.jar:./bin:../../etc/Frequency/ CaesarTest.java ../../lib/Java/Caesar.java ../../lib/Java/CaesarExploits.java ../../etc/Alphabet/Java/Alphabet.java
mv ../../lib/Java/Caesar.class ./bin/
mv ../../lib/Java/CaesarExploits.class ./bin/
mv ../../etc/Alphabet/Java/Alphabet.class ./bin/
mv ./CaesarTest.class ./bin/
cd ./bin/
java -cp junit.jar:org.hamcrest.core_1.1.0.v20090501071000.jar:./ org.junit.runner.JUnitCore CaesarTest
echo '**************Test Caesar*****************'
