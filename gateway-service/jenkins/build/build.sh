#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

docker build -t bsessevmez/gateway-service:latest .
