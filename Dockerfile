# Taurus Dockerfile
FROM blazemeter/taurus:1.14.2

EXPOSE 7080

# Change timezone to KST
ENV TZ=Asis/Seoul
RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Add Taurus Config file
COPY src/main/resources/taurus/conf/my-config.yaml /bzt-configs/my-config.yaml

# Add running script
COPY src/main/resources/taurus/scripts/init.sh /load-test/init.sh
RUN chmod +x /load-test/init.sh

# Install open-jdk8
RUN apt-get update -y
RUN apt-get install -y openjdk-8-jdk

# Install aws-cli v2
ADD https://awscli.amazonaws.com/awscli-exe-linux-x86_64.zip /aws/awscliv2.zip
RUN unzip /aws/awscliv2.zip -d /aws
RUN /aws/aws/install
RUN aws --version

# Add Java Application for Test
COPY target/load-test-*.jar /load-test/load-test.jar

# Create Result Directories
RUN mkdir -p /result/reports
RUN mkdir -p /result/artifacts

ENTRYPOINT ["java", "-jar", "/load-test/load-test.jar"]
