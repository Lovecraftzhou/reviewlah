#!/bin/bash

# shellcheck disable=SC2164
docker stop jenkins reviewlah-admin reviewlah-admin-vue reviewlah-comment reviewlah-customer reviewlah-eureka reviewlah-gateway reviewlah-merchant reviewlah-message
docker rm reviewlah-admin reviewlah-admin-vue reviewlah-comment reviewlah-customer reviewlah-eureka reviewlah-gateway reviewlah-merchant reviewlah-message
cd /var/jenkins_home/workspace/reviewlah-mic
docker compose up -d --build
docker start jenkins
