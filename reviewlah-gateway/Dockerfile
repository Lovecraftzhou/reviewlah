FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-gateway
COPY target/reviewlah-gateway.jar reviewlah-gateway.jar
EXPOSE 8081
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","-Xmx512m","reviewlah-gateway.jar"]
