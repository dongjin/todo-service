FROM openjdk:8-jdk-alpine
# ADD /Users/dson/workspace/todo-service/target/todo-service-0.0.1-SNAPSHOT.jar todo-service-0.0.1-SNAPSHOT.jar
# ADD ../../workspace/todo-service/target/todo-service-0.0.1-SNAPSHOT.jar todo-service-0.0.1-SNAPSHOT.jar

EXPOSE 8080
COPY ./target/todo-service-0.0.1-SNAPSHOT.jar todo-service-0.0.1-SNAPSHOT.jar

ENTRYPOINT ["sh","-c","java -jar /todo-service-0.0.1-SNAPSHOT.jar"]

