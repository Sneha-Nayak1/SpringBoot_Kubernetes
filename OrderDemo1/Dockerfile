FROM openjdk:22
VOLUME /tmp
ADD target/OrderDemo1-0.0.1-SNAPSHOT.jar spring-docker1.jar
ENTRYPOINT [ "java","-jar","spring-docker1.jar"]








#FROM openjdk:22-jdk-bullseye
#RUN apt-get update && apt-get install -y maven
#WORKDIR /app
#COPY pom.xml /app
#COPY src /app/src
#RUN mvn clean package -DskipTests
#ENTRYPOINT ["java", "-jar", "target/OrderDemo1-0.0.1-SNAPSHOT.jar"]




