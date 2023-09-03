FROM gradle:7.4-jdk17-alpine as builder
WORKDIR /build

# 그래들 파일이 변경되었을 때만 새롭게 의존패키지 다운로드 받게함.
COPY build.gradle settings.gradle /build/
RUN gradle build -x test --parallel --continue > /dev/null 2>&1 || true

# 빌더 이미지에서 애플리케이션 빌드
COPY . /build
RUN gradle build -x test --parallel

# APP
FROM openjdk:17.0-slim
WORKDIR /app

# 빌더 이미지에서 jar 파일만 복사
#COPY --from=builder /build/build/libs/*-SNAPSHOT.jar ./app.jar
COPY --from=builder /build/build/libs/arrangingseats-0.0.1-SNAPSHOT.jar .

RUN chmod +x arrangingseats-0.0.1-SNAPSHOT.jar

EXPOSE 8085

# root 대신 nobody 권한으로 실행
# "-Djava.security.egd=file:/dev/./urandom"
USER nobody
ENTRYPOINT ["java","-jar","-Dsun.net.inetaddr.ttl=0","arrangingseats-0.0.1-SNAPSHOT.jar"]


# FROM openjdk:17-alpine

# WORKDIR /app

# ARG JAR_PATH=./build/libs

# COPY ${JAR_PATH}/arrangingseats-0.0.1-SNAPSHOT.jar arrangingseats-0.0.1-SNAPSHOT.jar

# CMD ["java","-jar","/app/arrangingseats-0.0.1-SNAPSHOT.jar"]

# FROM openjdk:17-alpine

# RUN microdnf install findutils
# RUN apk update && apk add findutils

# WORKDIR /app/arranginseats_backend
# COPY . /app/arranginseats_backend
# VOLUME /tmp

# RUN chmod +x run.sh && gradle updateLib

# EXPOSE 8080

# CMD ["gradle"]
# CMD [ "sh" , "run.sh" ]