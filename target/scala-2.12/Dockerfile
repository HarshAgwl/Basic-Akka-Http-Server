FROM openjdk:8-jre-alpine
RUN mkdir -p /opt/app
WORKDIR /opt/app
COPY ./akka-http-app-assembly-0.1.jar ./
CMD ["java","-jar","./akka-http-app-assembly-0.1.jar"]
