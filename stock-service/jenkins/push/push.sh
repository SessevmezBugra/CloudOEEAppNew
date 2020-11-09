#!/bin/bash

echo "********************"
echo "** Pushing image ***"
echo "********************"

echo "** Logging in ***"
docker login -u bsessevmez -p $PASS
echo "*** Pushing image ***"
docker push bsessevmez/stock-service:latest
