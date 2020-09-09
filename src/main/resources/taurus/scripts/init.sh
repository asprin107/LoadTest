#!/usr/bin/env bash

# "TEST_COUNT" must be defined as environment.

# Taurus Init Script

HOST_NAME=$(hostname)
TEST_DATE=$(date +'%Y/%m/%d')
TEST_INIT_TIME=$(date +'%Y/%m/%d %H:%M:%S:%3N')
RSLT_SAVE_S3_LOCATION=s3://awsdc-s3-pss-dev-pjbak/load-test
WAIT_TELEGRAF_TRANSFER=5

echo "init script."
# echo "param is : \"$@\""

# Run Load Test
# sh -c "bzt -l /tmp/artifacts/bzt.log \"$@\"" "ignored"
sh -c "bzt -l /tmp/artifacts/bzt.log my-config.yaml"

# Upload All Result Data to S3
aws --version
aws s3 cp /result/reports/ ${RSLT_SAVE_S3_LOCATION}/${TEST_DATE}/${TEST_COUNT}/${HOST_NAME}/reports --recursive
aws s3 cp /result/artifacts/ ${RSLT_SAVE_S3_LOCATION}/${TEST_DATE}/${TEST_COUNT}/${HOST_NAME}/artifacts --recursive

# Transfer Result Data to Telegraf
# There is nothing to do. Taurus and Telegraf are shared reports volume.

# WAit Telegraf Transfer Result Data
# It must be bigger than time interval of telegraf
for (( i = 0; i < ${WAIT_TELEGRAF_TRANSFER}; i++ )); do
  sleep 1s
  echo "$[$i+1]s"
done

echo "Test Successfully Finished ."

exit 0
