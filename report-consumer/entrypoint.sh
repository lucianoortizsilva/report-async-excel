#!/bin/sh

while ! nc -z container-rabbitmq 5672 ; do
    echo "###############################################"
    echo "######## Aguardando container-rabbitmq ########"
    echo "###############################################"
    sleep 20
done

java -jar -Dspring.profiles.active=${ENV} target/report-consumer.jar