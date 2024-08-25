#!/bin/bash

# 서비스가 정상적으로 실행 중인지 확인
SERVICE_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost/user)

if [ "$SERVICE_STATUS" -eq 200 ]; then
  echo "Service is running successfully."
  exit 0
else
  echo "Service failed to start."
  exit 1
fi