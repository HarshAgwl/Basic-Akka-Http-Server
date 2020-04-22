FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
ARG jarName
ARG jarPath
COPY $jarPath ./
CMD ["java","-jar","./$jarName"]