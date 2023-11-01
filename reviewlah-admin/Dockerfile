FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-admin
COPY target/reviewlah-admin.jar reviewlah-admin.jar
EXPOSE 8086
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","reviewlah-admin.jar"]
