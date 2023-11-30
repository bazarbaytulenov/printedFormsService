FROM xldevops/jdk17-lts

ARG JAR_FILE=build/libs/printedFormsService-app.jar

RUN mkdir /app

COPY ${JAR_FILE} /app/spring-boot-application.jar

ENTRYPOINT ["java", "-Djava.security.egd=file:/dev/./urandom", "-jar","/app/spring-boot-application.jar"]
