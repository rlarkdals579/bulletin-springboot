#!/bin/bash

REPOSITORY=/home/ec2-user/app/step2
PROJECT_NAME=springboot-webservice

echo "> Copy Build file"

cp $REPOSITORY/zip/*.jar $REPOSITORY/

echo "> Checking current application's PID"

CURRENT_PID=$(pgrep -fl springboot-webservice | grep java | awk '{print $1}')

echo "Current application PID ": $CURRENT_PID"

if [ -z "$CURRENT_PID" ]; then
    echo "> There is no current running application. Exit denied"
else
    echo "> kill -15 $CURRENT_PID"
    kill -15 $CURRENT_PID
    sleep 5
fi

echo "> Deploying new application"

JAR_NAME=$(ls -tr $REPOSITORY/*.jar | tail -n 1)

echo "> JAR Name: $JAR_NAME"

echo "> $JAR_NAME : permit access"

chmod +x $JAR_NAME

echo "> $JAR_NAME execute"

nohup java -jar \
    -Dspring.config.location=classpath:/application.properties,classpath:/application-real.properties,/home/ec2-user/app/application-oauth.properties,/home/ec2-user/app/application-real-db.properties \
    -Dspring.profiles.active=real \
    $JAR_NAME > $REPOSITORY/nohup.out 2>&1 &