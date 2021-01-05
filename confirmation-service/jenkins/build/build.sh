#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd confirmation-service/ && docker build -t bsessevmez/confirmation-service:latest .
