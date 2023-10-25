FROM arm64v8/amazoncorretto:17-alpine3.18-jdk AS builder
LABEL authors="cinea"

WORKDIR /usr/src/forum

COPY pom.xml ./
COPY api-base/pom.xml ./api-base/
COPY course-service/pom.xml ./course-service/
COPY forum/pom.xml ./forum/
COPY forum-service/pom.xml ./forum-service/
COPY log-service/pom.xml ./log-service/
COPY user-service/pom.xml ./user-service/

RUN mvn -B dependency:go-offline

COPY . .

RUN mvn -B package

FROM arm64v8/amazoncorretto:17-alpine3.18
LABEL authors="cinea"

WORKDIR /usr/bin

COPY --from=builder /usr/src/forum/forum/target/forum-0.0.1-SNAPSHOT.jar .

EXPOSE 9001
ENTRYPOINT ["java","-jar","forum-0.0.1-SNAPSHOT.jar"]
