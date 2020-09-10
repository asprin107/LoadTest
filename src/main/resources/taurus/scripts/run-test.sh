#!/usr/bin/env bash

LOAD_GENERATORS=$(aws ecs describe-tasks --cluster awsdc-ecl-pss-dev-mgr --tasks $(aws ecs list-tasks --cluster awsdc-ecl-pss-dev-mgr --family awsdc-etk-pss-dev-load-test | jq -r .taskArns[]) | jq '.tasks[].attachments[].details[] | select(.name == "privateIPv4Address")' | jq -r .value)

LOAD_GENERATORS_ARR=($(echo ${LOAD_GENERATORS} | tr '  ' "\n"))

echo "############### health check ###############"
for LOAD_SERVER in "${LOAD_GENERATORS_ARR[@]}"; do
  echo "${LOAD_SERVER}:"
  curl http://${LOAD_SERVER}:7080/isAlive
  echo ""
done

echo "############### init load test ###############"
for LOAD_SERVER in "${LOAD_GENERATORS_ARR[@]}"; do
  echo "${LOAD_SERVER}:"
  curl http://${LOAD_SERVER}:7080/init
  echo ""
done

#for LOAD_SERVER in "${LOAD_GENERATORS_ARR[@]}"; do
#  curl http://${LOAD_SERVER}/actuator/shutdown
#done

exit 0
