#!/bin/bash

echo "********************"
echo "** Pushing image ***"
echo "********************"

echo "** Logging in ***"
docker login -u bsessevmez -p $PASS

docker push bsessevmez/auth-service:latest
