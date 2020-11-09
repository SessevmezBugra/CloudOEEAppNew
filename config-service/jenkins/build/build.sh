#!/bin/bash


echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd config-service/ && docker build -t bsessevmez/config-service:latest .
