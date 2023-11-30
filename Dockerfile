FROM amazoncorretto:17.0.7-alpine

COPY build/libs/printedFormsService-app.jar /app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app.jar"]