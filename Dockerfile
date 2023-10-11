FROM openjdk:17-oracle
MAINTAINER reviewlah
COPY target/reviewlah.jar reviewlah.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","reviewlah.jar"]