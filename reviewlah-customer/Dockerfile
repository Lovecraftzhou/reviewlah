FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-customer
COPY target/reviewlah-customer.jar reviewlah-customer.jar
EXPOSE 8082
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","reviewlah-customer.jar"]
