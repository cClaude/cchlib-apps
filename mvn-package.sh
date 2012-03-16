#!/bin/bash
#
# #########################################################
#
# #########################################################
#
# #########################################################
#
MVN="${MAVEN_HOME}/bin/mvn"

mvn clean package
if [ ! "$?" -eq "0" ];
then
  echo "[ERROR] in mvn clean package"
  exit 1
fi
java -jar CNAMTS-B2TransformApp/target/CNAMTS-B2TransformApp-0.0.1-SNAPSHOT*.jar
