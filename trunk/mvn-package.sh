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

java -jar CNAMTS-B2TransformApp/target/CNAMTS-B2TransformApp-0.0.1-SNAPSHOT*.jar
