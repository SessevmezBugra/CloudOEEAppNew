#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

docker build -t bsessevmez/eureka-server:latest .
