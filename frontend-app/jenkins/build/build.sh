#!/bin/bash

echo "****************************"
echo "** Building Docker Image ***"
echo "****************************"

cd frontend-app/ && docker build -t bsessevmez/frontend-app:latest .
