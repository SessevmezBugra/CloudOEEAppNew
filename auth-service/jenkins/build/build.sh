#!/bin/bash


echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd auth-service/ && docker build -t bsessevmez/auth-service:latest .
