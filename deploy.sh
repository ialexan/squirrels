#!/bin/bash

TOMCAT=/home/ialexan/apache-tomcat-7.0.30

cp target/squirrels.war $TOMCAT/webapps
rm -rf $TOMCAT/webapps/squirrels
