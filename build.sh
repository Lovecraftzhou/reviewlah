#!/bin/bash

# shellcheck disable=SC2164
docker stop reviewlah-back reviewlah-vue reviewlah-jenkins reviewlah-admin reviewlah-admin-vue reviewlah-comment reviewlah-customer reviewlah-eureka reviewlah-gateway reviewlah-merchant reviewlah-message
#docker rm reviewlah-admin reviewlah-admin-vue reviewlah-comment reviewlah-customer reviewlah-eureka reviewlah-gateway reviewlah-merchant reviewlah-message
cd /home/jenkins_home/workspace/reviewlah-mic
docker compose up -d --build
docker start reviewlah-jenkins
