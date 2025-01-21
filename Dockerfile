#stage 1 build

FROM eclipse-temurin:latest AS build
WORKDIR /app

RUN apt-get update && apt-get install -y findutils

#layer 1
COPY build.gradle settings.gradle gradlew.bat gradlew ./
COPY gradle gradle

RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

#layer 2
COPY src src
RUN ./gradlew bootJar --no-daemon

#stage 2 run

FROM eclipse-temurin:latest
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]