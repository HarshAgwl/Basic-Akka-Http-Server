#!/bin/bash
LINES_IN_DESCRIBE_SERVICE_RESPONSE="$(kubectl describe service basic-akka-http-app-service | wc -l)"
j2 ops/kubernetes/deployment-definition.j2.yml > ops/kubernetes/deployment-definition.yml
if [ $LINES_IN_DESCRIBE_SERVICE_RESPONSE -gt 1 ]
then
  kubectl apply -f ops/kubernetes/deployment-definition.yml --record
else
  kubectl create -f ops/kubernetes/deployment-definition.yml --record
  kubectl create -f ops/kubernetes/service-definition.yml
fi