#!/bin/bash

# 최대 대기 시간 (초)
MAX_WAIT_TIME=120
# 대기 간격 (초)
SLEEP_INTERVAL=5

# 경과 시간 초기화
elapsed_time=0

# 서비스가 정상적으로 실행될 때까지 반복 확인
while [ "$elapsed_time" -lt "$MAX_WAIT_TIME" ]; do
    # 서비스 상태 확인
    SERVICE_STATUS=$(curl -s -o /dev/null -w "%{http_code}" http://localhost:80/user)

    if [ "$SERVICE_STATUS" -eq 200 ]; then
        echo "Service is running successfully."
        exit 0
    fi

    echo "Waiting for service to start... ($elapsed_time/$MAX_WAIT_TIME seconds)"
    sleep $SLEEP_INTERVAL
    elapsed_time=$((elapsed_time + SLEEP_INTERVAL))
done

# 최대 대기 시간 내에 서비스가 시작되지 않으면 실패 처리
echo "Service failed to start within $MAX_WAIT_TIME seconds."
exit 1