# How to Start Load Test

## Build

This project use only for docker container. This binary doesn't work as expected.

First, you have to build java application using maven.

```
mvn clean package
```

After that, you have build docker image

```
docker build -t [image_name]:[tag] .
```

## How to Initiate Load Test
Load Test run using below.

```
curl -X GET {host}:{port}/init
```

Then, It initiates taurus load test service.
When test finished, If you want to do any other job, please add your job into 
`resources/scripts/init.sh` or create your own service.


## Terminate Load Test Server
This project can be terminated using below. (with spring actuator)

```
curl -X POST {host}:{port}/actuator/shutdown
```
