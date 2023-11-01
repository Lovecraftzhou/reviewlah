FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-back
COPY target/reviewlah.jar reviewlah.jar
EXPOSE 8080
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","reviewlah.jar"]
