#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd stock-service/ && docker build -t bsessevmez/stock-service:latest .
