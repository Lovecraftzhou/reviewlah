#!/bin/bash

docker rm reviewlah-back reviewlah-vue
docker compose up -d --build
