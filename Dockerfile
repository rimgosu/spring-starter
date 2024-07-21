# 베이스 이미지 설정
FROM openjdk:17-jdk-slim

# 작업 디렉터리 설정
WORKDIR /app

# 빌드 도구 및 소스 파일 복사
COPY gradlew .
COPY gradle gradle
COPY build.gradle .
COPY settings.gradle .
COPY src src

# Gradle 실행 권한 부여
RUN chmod +x ./gradlew

# 프로젝트 빌드 (테스트 건너뛰기)
RUN ./gradlew clean build -x test --no-daemon

# 이미지에 jar 파일 복사
COPY build/libs/*.jar app.jar

# 컨테이너 시작 시 실행할 명령어 설정
ENTRYPOINT ["java","-jar","/app/app.jar"]
