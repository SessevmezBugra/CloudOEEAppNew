#!/bin/bash

echo "***************************"
echo "** Building jar ***********"
echo "***************************"

WORKSPACE=/var/lib/jenkins/workspace/pipeline-config-service

docker run --rm  -v  $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app/config-service maven:3-alpine "$@"
