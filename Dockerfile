FROM openjdk:8

COPY ./target/Restaurant-Service.jar restaurantservice.jar

EXPOSE 8081

CMD ["java","-jar","-Dspring.profile.active=local","restaurantservice.jar"]