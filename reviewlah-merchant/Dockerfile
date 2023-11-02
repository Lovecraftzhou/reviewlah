FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-merchant
COPY target/reviewlah-merchant.jar reviewlah-merchant.jar
EXPOSE 8083
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","-Xmx512m","reviewlah-merchant.jar"]
