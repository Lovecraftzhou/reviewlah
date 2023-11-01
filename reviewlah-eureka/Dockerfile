FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-eureka
COPY target/reviewlah-eureka.jar reviewlah-eureka.jar
EXPOSE 8761
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","reviewlah-eureka.jar"]
