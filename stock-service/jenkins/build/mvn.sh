#!/bin/bash

echo "***************************"
echo "** Building jar ***********"
echo "***************************"

WORKSPACE=/var/lib/jenkins/workspace/pipeline-stock-service

docker run --rm  -v  $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app/stock-service maven:3-alpine "$@"
