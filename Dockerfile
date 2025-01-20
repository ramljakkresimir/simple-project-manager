#stage 1 build

FROM amazoncorretto:8u432-al2023 AS build
WORKDIR /app

RUN yum update -y && yum install -y findutils

#layer 1
COPY build.gradle settings.gradle gradlew.bat gradlew gradle/ ./
RUN chmod +x gradlew
RUN ./gradlew dependencies --no-daemon

#layer 2
COPY src src
RUN ./gradlew bootJar --no-daemon

#stage 2 run

FROM amazoncorretto:8u432-al2023-jre
WORKDIR /app
COPY --from=build /app/build/libs/*.jar app.jar
CMD ["java","-jar","app.jar"]