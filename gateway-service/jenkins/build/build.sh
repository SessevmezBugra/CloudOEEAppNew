#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd gateway-service/ && docker build -t bsessevmez/gateway-service:latest .
