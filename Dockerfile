FROM openjdk:8
EXPOSE 8080
ADD target/unif-0.0.1-SNAPSHOT.jar unif-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/unif-0.0.1-SNAPSHOT.jar"]