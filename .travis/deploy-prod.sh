#!/bin/bash
docker build -t swarm.cherement.nl:5000/backend-prod .
docker push swarm.cherement.nl:5000/backend-prod
ssh -t -i ./id_rsa travis-ci@swarm.cherement.nl "~/builds/backend-prod/rebuild-docker.sh"
