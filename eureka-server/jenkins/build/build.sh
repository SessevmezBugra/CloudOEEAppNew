#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd eureka-server/ && docker build -t bsessevmez/eureka-server:latest .
