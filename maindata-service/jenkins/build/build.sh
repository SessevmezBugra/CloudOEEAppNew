#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd maindata-service/ && docker build -t bsessevmez/maindata-service:latest .
