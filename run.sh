#!/bin/bash
echo "###################"
echo "Building Executable"
echo "###################"

mvn clean validate compile package

mv ./target/QRGenerator.jar .

echo "###################"
echo "Executing Binary"
echo "###################"

java -Dfile.encoding=UTF-8 -jar ./QRGenerator.jar "$@"

