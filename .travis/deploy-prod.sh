#!/bin/bash
docker build --build-arg JAR_FILE=./build/libs/*.jar -t swarm.cherement.nl:5000/backend-prod .
docker push swarm.cherement.nl:5000/backend-prod
ssh -t -i ./id_rsa travis-ci@swarm.cherement.nl "~/builds/backend-prod/rebuild-docker.sh"
