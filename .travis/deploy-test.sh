#!/bin/bash
docker build --build-arg JAR_FILE=./build/libs/*.jar -t swarm.cherement.nl:5000/backend-test .
docker push swarm.cherement.nl:5000/backend-test
ssh -t -i ./id_rsa travis-ci@swarm.cherement.nl "~/builds/backend-test/rebuild-docker.sh"
