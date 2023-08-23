FROM maven:3.6.3-jdk-11-slim@sha256:68ce1cd457891f48d1e137c7d6a4493f60843e84c9e2634e3df1d3d5b381d36c
LABEL author="lucianoortizsilva@gmail.com"
RUN apt-get update
RUN apt-get install -y netcat
RUN apt-get install -y libfreetype6
RUN apt-get install -y fontconfig
WORKDIR /app
COPY pom.xml pom.xml
COPY src src
COPY entrypoint.sh /opt/bin/entrypoint.sh
RUN chmod 777 /opt/bin/entrypoint.sh
RUN mvn clean package
EXPOSE 8080