FROM adoptopenjdk:17-jre-hotspot

COPY build/libs/printedFormsService-app.jar /app.jar

EXPOSE 8081

CMD ["java", "-jar", "/app.jar"]