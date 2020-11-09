#!/bin/bash


echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd order-service/ && docker build -t bsessevmez/order-service:latest .
