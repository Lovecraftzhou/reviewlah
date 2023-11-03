FROM openjdk:17-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-back
COPY target/reviewlah-back.jar reviewlah-back.jar
EXPOSE 8181
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","-Xmx512m","reviewlah-back.jar"]
