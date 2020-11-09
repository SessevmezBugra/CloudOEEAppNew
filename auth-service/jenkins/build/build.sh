#!/bin/bash


echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

docker build -t bsessevmez/auth-service:latest .
