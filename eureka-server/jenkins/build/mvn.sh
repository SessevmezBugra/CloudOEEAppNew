#!/bin/bash

echo "***************************"
echo "** Building jar ***********"
echo "***************************"

WORKSPACE=/var/lib/jenkins/workspace/pipeline-eureka-server

docker run --rm  -v  $WORKSPACE:/app -v /root/.m2/:/root/.m2/ -w /app/eureka-server maven:3-alpine "$@"
