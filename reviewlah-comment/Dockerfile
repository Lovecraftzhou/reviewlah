FROM openjdk:18-oracle
MAINTAINER reviewlah
WORKDIR /reviewlah-comment
COPY target/reviewlah-comment.jar reviewlah-comment.jar
EXPOSE 8084
ENV LANG C.UTF-8
ENTRYPOINT ["java","-jar","-Xmx512m","reviewlah-comment.jar"]
