#!/bin/bash
if [ $GO_DEPENDENCY_LABEL_PIPELINE_MATERIAL -gt 1 ]
then
  kubectl set image deployment/example-akka-http-server basic-akka-http-app=basic-akka-http-app:$GO_DEPENDENCY_LABEL_PIPELINE_MATERIAL
else
	kubectl create deployment example-akka-http-server --image=itsharshag/basic-akka-http-app:4
	kubectl expose deployment example-akka-http-server --type=NodePort --port=8080
fi