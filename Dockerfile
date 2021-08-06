FROM openjdk:8
EXPOSE 9293
ADD target/unif-0.0.1-SNAPSHOT.jar unif-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","/unif-0.0.1-SNAPSHOT.jar"]