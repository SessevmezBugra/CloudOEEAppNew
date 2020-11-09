#!/bin/bash


echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

docker build -t bsessevmez/order-service:latest .
