FROM openjdk:10
WORKDIR usr/src
ENV MYSQL_DATABASE=user
ENV MYSQL_USER=root
ENV MYSQL_PASSWORD=root
ENV MYSQL_CI_URL=jdbc:mysql://localhost:3306/user
ADD ./target/user-service-0.0.1-SNAPSHOT.jar /usr/src/user-service-0.0.1-SNAPSHOT.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/usr/src/user-service-0.0.1-SNAPSHOT.jar"]