#!/bin/bash

tag=$(git symbolic-ref HEAD 2>/dev/null | awk -F '/' '{print $NF}')

docker kill $(docker ps -q)
docker rm $(docker ps -a -q)

mvn clean package docker:build -DdockerImageTags=$tag
docker run -p :8080 -d desafio-bluesoft:$tag

read -p 'Aperte enter' teste;
