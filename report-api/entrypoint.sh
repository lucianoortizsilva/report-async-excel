#!/bin/sh

while ! nc -z container-rabbitmq 5672 ; do
    echo "###############################################"
    echo "######## Aguardando container-rabbitmq ########"
    echo "###############################################"
    sleep 30
done

/report-api