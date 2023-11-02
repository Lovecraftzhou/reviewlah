FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-message
COPY target/reviewlah-message.jar reviewlah-message.jar
EXPOSE 8085
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","-Xmx512m","reviewlah-message.jar"]
